package com.salapp.petclinic.repository;

import com.salapp.petclinic.model.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Stainley Lebron
 * @since 2/25/20.
 */


@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ClientRepository clientRepository;

    @Test
    public void whenFindByName_ThenReturnClient() {
        // Given
        Client client = new Client("Stainley", "Active");
        entityManager.persist(client);
        entityManager.flush();

        // When
        Client found = clientRepository.findClientByName(client.getName());

        // Then
        assertThat(found.getName()).isEqualTo(client.getName());

    }

}
