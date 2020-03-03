package com.salapp.petclinic.services;


import com.salapp.petclinic.dto.ClientRequest;
import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Stainley Lebron
 * @since 3/1/20.
 */

@RestController()
@RequestMapping("/api")
@Slf4j
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
        return name;
    }

    @DeleteMapping(value = "/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteClient(@PathVariable String id) {
        try {
            clientRepository.deleteById(Long.parseLong(id));
            return "Successful";
        } catch (Exception e) {
            return null;
        }
    }
}
