package com.salapp.petclinic.services;


import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.repository.ClientRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Stainley Lebron
 * @since 3/1/20.
 */

@RestController
@RequestMapping("/api")
public class ClientServices {

    ClientRepository clientRepository;

    public ClientServices(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping("/")
    public Client save(Client client) {
        return clientRepository.save(client);
    }

}
