package com.salapp.petclinic.services;

import com.salapp.petclinic.dto.ClientRequest;
import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.valueextraction.ExtractedValue;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Stainley Lebron
 * @since 3/1/20.
 */


public class ClientServiceTest {

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    ClientServices clientServices;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test()
    public void when_save_it_should_return_client() {
        Client client = new Client();
        //ClientRequest createClientRequest = new ClientRequest();
        client.setName("Test Name");

        ClientRequest clientRequest = new ClientRequest(client);
        Assertions.assertNotNull(client);

        when(clientRepository.save(any(Client.class))).thenReturn(new Client());
        Client clientCreated = clientServices.save(clientRequest);
        //String expectedValue = "Test Name";
        //Assertions.assertEquals(expectedValue, clientCreated.getName());
    }

    @Test
    public void show_all_client() {
        clientServices.getAllClient();
    }

    @Test
    public void findClientByName() {
        String expected = "Test Client";
        Assertions.assertEquals(expected, clientServices.getClientName("Test Client"));
    }

    @Test
    public void delete_client_by_id() throws Exception {
        try {
            clientServices.deleteClient(11L);
        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

}
