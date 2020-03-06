package com.salapp.petclinic.repository;

import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.util.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

/**
 * @author Stainley Lebron
 * @since 2/25/20.
 */

@AutoConfigureMockMvc
//@DataJpaTest(showSql = true)
public class ClientRepositoryTest {


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getClient_returnClientDetails() {
        Client client = new Client("Test", Status.ACTIVE);
        ClientRepository mock = Mockito.mock(ClientRepository.class);

        given(mock.findClientByName(anyString())).willReturn(new Client("Test", Status.ACTIVE));

        /*Client savedClient = entityManager.persistAndFlush(new Client("Test", Status.ACTIVE));
        Client client = clientRepository.findClientById(1L);

        assertThat(client.getName()).isEqualTo(savedClient.getName());
        assertThat(client.getStatus()).isEqualTo(savedClient.getStatus());*/
    }
}
