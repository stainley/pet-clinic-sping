package com.salapp.petclinic.services;

import com.salapp.petclinic.repository.ClientRepository;
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
@WebMvcTest
public class ClientServiceIT {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClientRepository clientRepository;

    @Test
    public void contextLoad() throws Exception {

        Mockito.when(clientRepository.findAll()).thenReturn(Collections.emptyList());

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/all/").accept(MediaType.APPLICATION_JSON)
        ).andReturn();

        System.out.println(mvcResult.getResponse());
        Mockito.verify(clientRepository).findAll();
    }
}
