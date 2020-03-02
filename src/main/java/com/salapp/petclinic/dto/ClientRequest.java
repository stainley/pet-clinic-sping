package com.salapp.petclinic.dto;

import com.salapp.petclinic.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Stainley Lebron
 * @since 3/1/20.
 */
@Component
public class ClientRequest {

    public Client client;

    public ClientRequest() {
    }

    public ClientRequest(Client client) {
        this.client = client;
    }

    public void setName(String name) {
        client.setName(name);
    }

    public Client getClient() {
        return this.client;
    }
}
