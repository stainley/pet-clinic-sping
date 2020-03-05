package com.salapp.petclinic.repository;

import com.salapp.petclinic.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Stainley Lebron
 * @since 2/25/20.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findClientByName(String client);
    Client findClientById(Long id);

}
