package com.salapp.petclinic.repository;

import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.util.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Stainley Lebron
 * @since 2/25/20.
 */

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void getClient_returnClientDetails() throws Exception {
        Client savedClient = entityManager.persistAndFlush(new Client("Test", Status.ACTIVE));
        Client client = clientRepository.findClientById(1L);

        assertThat(client.getName()).isEqualTo(savedClient.getName());
        assertThat(client.getStatus()).isEqualTo(savedClient.getStatus());
    }
}
