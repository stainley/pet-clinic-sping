package com.salapp.petclinic.services.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salapp.petclinic.PetClinicApplication;
import com.salapp.petclinic.dto.ClientRequest;
import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.services.ClientServices;
import com.salapp.petclinic.util.Status;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Stainley Lebron
 * @since 3/2/20.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PetClinicApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class ClientServiceIT {
    private ObjectMapper objectMapper = new ObjectMapper();
    private static HttpEntity<ClientRequest> request;
    private static MultiValueMap<String, ClientRequest> parts = new LinkedMultiValueMap<>();
    private static JSONObject clientJsonObject;
    private static HttpHeaders headers;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    ClientServices clientServices;

    @BeforeAll
    public static void runBeforeAllTestMethods() throws JSONException {
        clientJsonObject = new JSONObject();
        clientJsonObject.put("id", 1);
        clientJsonObject.put("name", "Test Value");

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Client client = new Client();
        client.setName("Test Client");
        client.setStatus(Status.ACTIVE);

        parts.put("clientRequest", Lists.list(new ClientRequest(client)));
        request = new HttpEntity<>(new ClientRequest(client), headers);
    }

    @Test
    public void contextLoads() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/all/")
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn();

        System.out.println(mvcResult.getResponse());

        /*mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/client/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        */
    }

    @Test
    public void save_client() throws JSONException, JsonProcessingException {
        String clientResponse = testRestTemplate.postForObject("/api/client/create", request, String.class, parts);
        JsonNode root = objectMapper.readTree(clientResponse);

        org.junit.jupiter.api.Assertions.assertNotNull(root);
        Assertions.assertThat(root.path("name").asText()).isEqualTo("Test Client");
        Assertions.assertThat(clientResponse).isNotNull();
    }

    @Test
    @DisplayName(value = "deleteById")
    public void delete_client_by_id() {
        testRestTemplate.delete("/api/delete/client/{id}", parts.getFirst("clientRequest").getClient().getId());
    }

}
