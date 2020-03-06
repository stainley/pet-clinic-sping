package com.salapp.petclinic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salapp.petclinic.PetClinicApplication;
import com.salapp.petclinic.dto.ClientRequest;
import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.services.ClientService;
import com.salapp.petclinic.util.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = PetClinicApplication.class
)
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ClientControllerIT {

    @MockBean
    private ClientService clientService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientController clientController;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void whenUserControllerInjected_thenNotNull() throws Exception {
        assertThat(clientController).isNotNull();
    }


    @Test
    public void createNewClient() throws Exception {
        Client client = new Client("Test", Status.ACTIVE);
        ClientRequest clientRequest = new ClientRequest(client);

        mockMvc.perform(
                post("/client/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(clientRequest))).andExpect(status().isOk())
        ;

        //Client clientFound = clientController.getClientByName("Test");
        //assertThat(clientFound.getStatus()).isEqualTo(Status.ACTIVE);
    }


    @DisplayName(value = "find_client_by_id")
    @Test
    @AfterTestMethod(value = "createNewClient")
    public void findClientById() throws Exception {

        given(clientService.getClientDetail(anyLong())).willReturn(new Client("Test", Status.ACTIVE));

        mockMvc.perform(
                get("/client/get/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Test"))

        ;
    }
}
