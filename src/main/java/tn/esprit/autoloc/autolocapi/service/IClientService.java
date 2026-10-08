package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {

    Client create(Client client);

    List<Client> findAll();

    Optional<Client> findById(Long id);

    Client update(Long id, Client client);

    void deleteById(Long id);
}
