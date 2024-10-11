package com.app.ClientService.services;

import com.app.ClientService.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client createClient(Client client);
    List<Client> getAllClients();
    Optional<Client> getClientById(Long id);
    Client updateClient(Client client);
    void deleteClientById(Long id);
}
