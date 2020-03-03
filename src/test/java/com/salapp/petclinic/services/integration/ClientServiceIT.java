package com.salapp.petclinic.services.integration;

import com.salapp.petclinic.PetClinicApplication;
import com.salapp.petclinic.dto.ClientRequest;
import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.services.ClientServices;
import org.assertj.core.api.Assertions;
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

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    ClientServices clientServices;

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
    public void save_client() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Client client = new Client();
        client.setName("Test Client");

        HttpEntity<ClientRequest> request = new HttpEntity<>(new ClientRequest(client), headers);
        Client clientResponse = testRestTemplate.postForObject("/api/createClient", request, Client.class);

        /*Assertions.assertThat(clientResponse.getStatusCode(), is(equalTo(HttpStatus.OK)));
        Assertions.assertThat(clientResponse.getHeaders().get("Test Client").get(0), is(equalTo("Sucessful")));*/

        Assertions.assertThat(clientResponse).isNotNull();
        System.out.println("TESTING: " + clientResponse.getId() + " - " + clientResponse.getName());

    }

    @Test
    @DisplayName(value = "deleteClientById")
    public void delete_client_by_id() {
        testRestTemplate.delete("/api/client/{id}", 1L);
    }

}
