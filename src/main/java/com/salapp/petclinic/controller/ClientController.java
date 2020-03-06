package com.salapp.petclinic.controller;

import com.salapp.petclinic.exception.ClientNotFoundException;
import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.services.ClientService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public void createClient(@RequestBody  Client client) {

    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public Client getClient(@PathVariable Long id) {
        return clientService.getClientDetail(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void clientNotFoundHandler(ClientNotFoundException ex) {
    }
}
