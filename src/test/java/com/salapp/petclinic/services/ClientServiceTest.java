package com.salapp.petclinic.services;

import com.salapp.petclinic.dto.ClientRequest;
import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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

    @Test()
    public void when_save_it_should_return_client() {
        ClientRequest createClientRequest = new ClientRequest();
        createClientRequest.setName("Test Client");
        Assertions.assertNotNull(clientServices);

        when(clientRepository.save(any(Client.class))).thenReturn(new Client());
        Client clientCreated = clientServices.save(createClientRequest);
    }
}
