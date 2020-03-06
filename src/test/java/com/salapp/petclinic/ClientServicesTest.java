package com.salapp.petclinic;

import com.salapp.petclinic.dto.ClientRequest;
import com.salapp.petclinic.exception.ClientNotFoundException;
import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.repository.ClientRepository;
import com.salapp.petclinic.services.ClientService;
import com.salapp.petclinic.util.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
//@WebMvcTest
public class ClientServicesTest {


    @MockBean
    private ClientRepository clientRepository;

    private ClientService clientService;


    @Before
    public void setUp() throws Exception {
        clientService = new ClientService(clientRepository);
    }

    @Test
    @DisplayName(value = "getClientDetails_return_Client_Info")
    public void getClientDetails_ReturnClientInfo() {
        given(clientRepository.findClientById(1L)).willReturn(new Client("Test", Status.ACTIVE));

        Client client = clientService.getClientDetail(1L);

        assertThat(client.getName()).isEqualTo("Test");
        assertThat(client.getStatus()).isEqualTo(Status.ACTIVE);
    }

    @Test(expected = ClientNotFoundException.class)
    public void getClientDetail_whenClientNotFound() {
        given(clientRepository.findClientById(anyLong())).willReturn(null);

        clientService.getClientDetail(1L);
    }

    @Test(expected = ClientNotFoundException.class)
    public void getClientDetailByName_whenClientIsNull() {
        clientService.getClientByName(null);
    }

    @Test
    public void createClient() {
        clientService.saveClient(new ClientRequest(any()));
    }

    @Test
    public void getClientByName() {
        given(clientService.getClientByName("Test")).willReturn(new Client("Test", Status.ACTIVE));
        assertThat(clientService.getClientByName("Test")).isEqualTo(new Client("Test", Status.ACTIVE));

    }

    @Test(expected = ClientNotFoundException.class)
    public void getClientDetail() {
        given(clientService.getClientDetail(anyLong())).willReturn(new Client("Test", Status.ACTIVE));
        assertThat(clientRepository.findClientById(1L)).isEqualTo(new Client("Test", Status.ACTIVE));
    }

    @Test
    public void deleteClientById() {
        clientService.deleteById(anyLong());
        assertThatObject(clientRepository).isNotNull();
    }
}

