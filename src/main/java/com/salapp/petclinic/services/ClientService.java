package com.salapp.petclinic.services;

import com.salapp.petclinic.dto.ClientRequest;
import com.salapp.petclinic.exception.ClientNotFoundException;
import com.salapp.petclinic.model.Client;
import com.salapp.petclinic.repository.ClientRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Cacheable("client")
    public Client getClientDetail(Long id) {
        Client client = clientRepository.findClientById(id);
        if (client == null) {
            throw new ClientNotFoundException();
        }
        return client;
    }

    public void saveClient(ClientRequest clientRequest) {
        clientRepository.saveAndFlush(clientRequest.getClient());
    }

    public Client getClientByName(String name) {
        if (name != null) {
            return clientRepository.findClientByName(name);
        } else {
            throw new ClientNotFoundException();
        }
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
