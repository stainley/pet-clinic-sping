package com.salapp.petclinic.controller;

import com.salapp.petclinic.dto.ClientRequest;
import com.salapp.petclinic.exception.ClientNotFoundException;
import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    private Logger logger = LoggerFactory.getLogger(ClientController.class);

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public void createClient(@RequestBody ClientRequest clientRequest) {
        if (clientRequest != null) {
            clientService.saveClient(clientRequest);
            logger.info(clientRequest.getClient().toString());
        }
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public Client getClient(@PathVariable Long id) {
        return clientService.getClientDetail(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void clientNotFoundHandler(ClientNotFoundException ex) {
        logger.error(ex.getMessage(), ex);
    }

    @GetMapping(path = "/getByName/{name}", consumes = "application/json", produces = "application/json")
    public Client getClientByName(@PathVariable String name) {
        return clientService.getClientByName(name);
    }
}
