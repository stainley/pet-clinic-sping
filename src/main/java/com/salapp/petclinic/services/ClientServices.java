package com.salapp.petclinic.services;


import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.repository.ClientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
