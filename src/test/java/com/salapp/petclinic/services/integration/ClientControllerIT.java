package com.salapp.petclinic.services.integration;

import com.salapp.petclinic.PetClinicApplication;
import com.salapp.petclinic.dto.ClientRequest;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PetClinicApplication.class
)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ClientControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void setUp() {

    }

    @DisplayName(value = "find_client_by_id")
    @Test
    public void findClientById() {

        // Act
        ResponseEntity<ClientRequest> response = testRestTemplate.getForEntity("/client/get/{id}", ClientRequest.class, 1L);

        //assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        //assertThat(response.getBody().getStatus()).isEqualTo(Status.ACTIVE);
        //assertThat(response.getBody().getClient().getName()).isEqualTo("Test");

    }
}
