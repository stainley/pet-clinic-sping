package com.salapp.petclinic.services;


import com.salapp.petclinic.dto.ClientRequest;
import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.repository.ClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Stainley Lebron
 * @since 3/1/20.
 */

@RestController("/api")
public class ClientServices {

    ClientRepository clientRepository;

    public ClientServices(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping(path = "/createClient", produces = "application/json")
    public Client save(ClientRequest clientRequest) {
        Client client = clientRequest.getClient();
        return clientRepository.save(client);
    }

    @GetMapping("/all")
    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }

    @PostMapping(path = "/getName", produces = "application/json")
    public String getClientName(@RequestBody String name) {
        System.out.println("Invoking API!!!");
        return name;
    }

}
