package com.salapp.petclinic.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salapp.petclinic.PetClinicApplication;
import com.salapp.petclinic.dto.ClientRequest;
import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.util.Status;
import org.assertj.core.util.Lists;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Stainley Lebron
 * @since 3/2/20.
 */
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PetClinicApplication.class
)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ClientServiceIT {

    private ObjectMapper objectMapper = new ObjectMapper();
    private static HttpEntity<ClientRequest> request;
    private static MultiValueMap<String, ClientRequest> parts = new LinkedMultiValueMap<>();
    private static HttpHeaders headers;

    @Autowired
    private TestRestTemplate testRestTemplate;


    @InjectMocks
    ClientService clientServices;


    @BeforeAll
    public static void runBeforeAllTestMethods() throws JSONException {
        JSONObject clientJsonObject = new JSONObject();
        clientJsonObject.put("id", 1);
        clientJsonObject.put("name", "Test Value");
        clientJsonObject.put("status", Status.ACTIVE);

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Client client = new Client();
        client.setName("Test Client");
        client.setStatus(Status.ACTIVE);

        parts.put("clientRequest", Lists.list(new ClientRequest(client)));

        request = new HttpEntity<>(new ClientRequest(client), headers);
    }


    @Test
    public void save_client() throws JSONException, JsonProcessingException {
        String clientResponse = testRestTemplate.postForObject("/api/client/create", request, String.class, parts);
        JsonNode root = objectMapper.readTree(clientResponse);

        org.junit.jupiter.api.Assertions.assertNotNull(root);
        assertThat(root.path("name").asText()).isEqualTo("Test Client");
        assertThat(clientResponse).isNotNull();
    }

    @Test
    @DisplayName(value = "delete_client_by_id")
    public void deleteClientById() {
        testRestTemplate.delete("/api/delete/client/{id}", parts.getFirst("clientRequest").getClient().getId());
    }


}
