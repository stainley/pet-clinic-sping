package com.salapp.petclinic.dto;

import com.salapp.petclinic.model.Client;
import org.springframework.stereotype.Component;

/**
 * @author Stainley Lebron
 * @since 3/1/20.
 */
@Component
public class ClientRequest {

    private Client client;

    public ClientRequest() {
    }

    public ClientRequest(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return this.client;
    }

    @Override
    public String toString() {
        return "ClientRequest{" +
                "client=" + client + "\tName: " + client.getName() + "\tStatus: " + client.getStatus() + '}';
    }
}
