package com.salapp.petclinic.services;


import com.salapp.petclinic.dto.ClientRequest;
import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.repository.ClientRepository;
import com.salapp.petclinic.util.Status;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Stainley Lebron
 * @since 3/1/20.
 */

@RestController()
@RequestMapping("/api")
public class ClientServices {

    ClientRepository clientRepository;

    public ClientServices(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Create a new client
     *
     * @param clientRequest
     * @return client
     */
    @PostMapping(path = "/client/create", produces = "application/json", consumes = "application/json")
    public Client save(@RequestBody ClientRequest clientRequest) {
        Client client = clientRequest.getClient();
        return clientRepository.saveAndFlush(client);
    }

    @GetMapping("/all")
    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }

    @GetMapping(path = "/client/find/{id}", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Client getClientById(@RequestBody @PathVariable Long id) {
        return new Client("Test", Status.ACTIVE);
    }

    /**
     * Delete a client by id
     *
     * @param id
     */
    @DeleteMapping(value = "/delete/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
    }
}
