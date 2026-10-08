package com.freelance_platform.service;

import com.freelance_platform.entity.Client;
import com.freelance_platform.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // GET ALL CLIENTS
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // GET CLIENT BY ID
    public Client getClientById(Integer id) {

        return clientRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Client not found with id: " + id
                        )
                );
    }

    // CREATE CLIENT
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    // UPDATE CLIENT
    public Client updateClient(Integer id, Client client) {

        Client existingClient =
                clientRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Client not found with id: " + id
                                )
                        );

        existingClient.setCompanyName(
                client.getCompanyName()
        );

        existingClient.setCompanyDescription(
                client.getCompanyDescription()
        );

        existingClient.setCompanyWebsite(
                client.getCompanyWebsite()
        );

        existingClient.setLocation(
                client.getLocation()
        );

        return clientRepository.save(existingClient);
    }

    // DELETE CLIENT
    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }
}