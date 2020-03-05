package com.salapp.petclinic.controller;

import com.salapp.petclinic.exception.ClientNotFoundException;
import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.services.ClientService;
import com.salapp.petclinic.util.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers = ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    @DisplayName(value = "getClient_ShouldReturn_Client")
    public void getClientShouldReturnClient() throws Exception {

        given(clientService.getClientDetail(anyLong())).willReturn(new Client("Test", Status.ACTIVE));

        mockMvc.perform(MockMvcRequestBuilders.get("/client/get/{id}", 1L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("Test"))
                .andExpect(MockMvcResultMatchers.jsonPath("status").value("ACTIVE"));

    }


    @Test
    @DisplayName(value = "getClient_not_Found")
    public void getClientNotFound() throws Exception {
        given(clientService.getClientDetail(anyLong())).willThrow(new ClientNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/client/get/{id}", 1L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
