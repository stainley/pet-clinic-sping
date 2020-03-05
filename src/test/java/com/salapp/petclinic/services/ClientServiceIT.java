package com.salapp.petclinic.services;

import com.salapp.petclinic.dto.ClientRequest;
import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

/**
 * @author Stainley Lebron
 * @since 3/2/20.
 */

public class ClientServiceIT {


    ClientServices clientServices;


    MockMvc mockMvc;


    ClientRepository clientRepository;


    public void contextLoad() throws Exception {

        Mockito.when(clientRepository.findAll()).thenReturn(Collections.emptyList());

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/all/").accept(MediaType.APPLICATION_JSON)
        ).andReturn();

        System.out.println(mvcResult.getResponse());
        Mockito.verify(clientRepository).findAll();
    }



    public void get_client_by_name() {
        Assertions.assertEquals("Test Client", clientServices.getClientById(1L));
    }


    public void save_client_by_clientRequest() {
        Client client = new Client();
        client.setName("Test Client");

        ClientRequest clientRequest = new ClientRequest(client);
        clientServices.save(clientRequest);
    }

}
