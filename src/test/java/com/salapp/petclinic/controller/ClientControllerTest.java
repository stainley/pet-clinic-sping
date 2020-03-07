package com.salapp.petclinic.controller;

import com.salapp.petclinic.dto.ClientRequest;
import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.services.ClientService;
import com.salapp.petclinic.util.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * @author Stainley Lebron
 * @since 3/6/20.
 */
@RunWith(SpringRunner.class)
public class ClientControllerTest {


    @MockBean
    private ClientService clientService;


    private ClientController clientController;

    @Before
    public void setUp() {
        clientController = new ClientController(clientService);
    }


    @Test
    public void getClientById() {
        given(clientService.getClientDetail(1L)).willReturn(new Client("Test", Status.ACTIVE));

        Client client = clientService.getClientDetail(1L);

        assertThat(client.getName()).isEqualTo("Test");
        assertThat(client.getStatus()).isEqualTo(Status.ACTIVE);
    }

    @Test
    public void deleteClientById() {
        // Given
        when(clientService.getClientDetail(1L)).thenReturn(new Client("Test", Status.ACTIVE)).thenReturn(null);

        // When
        final boolean  result = clientController.deleteClient(1L);

        // Then
        verify(clientService, times(1)).deleteById(1L);
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void createClient() {
        Client client = new Client("Pancho", Status.ACTIVE);

        when(clientService.getClientDetail(1L)).thenReturn(client).thenReturn(null);

        clientController.createClient(new ClientRequest(client));

        verify(clientService, times(1)).saveClient(client));
    }

}
