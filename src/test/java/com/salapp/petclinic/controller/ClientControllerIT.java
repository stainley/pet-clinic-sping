package com.salapp.petclinic.controller;

import com.salapp.petclinic.PetClinicApplication;
import com.salapp.petclinic.services.ClientService;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = PetClinicApplication.class
)
@TestPropertySource(locations = "classpath:application-test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientControllerIT {

    @MockBean
    private ClientService clientService;

    @Autowired
    private ClientController clientController;

    @Autowired
    public MockMvc mockMvc;

    @Test
    public void whenUserControllerInjected_thenNotNull() throws Exception {
        assertThat(clientController).isNotNull();
    }


    @Test
    public void createNewClient() throws Exception {
        String client = "{\"name\": \"Test\", \"status\" : \"ACTIVE\"}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/client/create")
                        .content(client)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }


    @DisplayName(value = "find_client_by_id")
    @Test
    @Order(2)
    public void findClientById() throws Exception {


        mockMvc.perform(
                MockMvcRequestBuilders.get("/client/get/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())

        ;


    }
}
