package com.freelance_platform.controller;

import com.freelance_platform.entity.Client;
import com.freelance_platform.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "https://freelance-platform-frontend-8wpj.vercel.app")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // GET ALL CLIENTS
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // GET CLIENT BY ID
    @GetMapping("/{id}")
    public Client getClient(@PathVariable Integer id) {
        return clientService.getClientById(id);
    }

    // CREATE CLIENT
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    // UPDATE CLIENT
    @PutMapping("/{id}")
    public Client updateClient(
            @PathVariable Integer id,
            @RequestBody Client client) {

        return clientService.updateClient(id, client);
    }

    // DELETE CLIENT
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
    }
}