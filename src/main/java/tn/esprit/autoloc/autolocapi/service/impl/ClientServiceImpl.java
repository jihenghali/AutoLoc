
package tn.esprit.autoloc.autolocapi.service.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.dto.ClientDTO;
import tn.esprit.autoloc.autolocapi.mapper.ClientMapper;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IClientRepository;
import tn.esprit.autoloc.autolocapi.service.IClientService;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientDTO create(ClientDTO dto) {
        Client client = clientMapper.toEntity(dto);

        if (client.getIdClient() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau client ne doit pas avoir d'identifiant");
        }

        validerClient(client);

        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    public ClientDTO findById(Long id) {
        return clientMapper.toDTO(findEntityById(id));
    }

    private Client findEntityById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client", id));
    }

    @Override
    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDTO)
                .toList();
    }

    @Override
    public ClientDTO update(Long id, ClientDTO dto) {
        Client client = clientMapper.toEntity(dto);

        Client existant = findEntityById(id);

        validerClient(client);

        existant.setNom(client.getNom());
        existant.setPrenom(client.getPrenom());
        existant.setEmail(client.getEmail());
        existant.setTelephone(client.getTelephone());
        existant.setNumPermis(client.getNumPermis());
        existant.setDateInscription(client.getDateInscription());

        return clientMapper.toDTO(clientRepository.save(existant));
    }

    @Override
    public void deleteById(Long id) {

        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client", id);
        }

        clientRepository.deleteById(id);
    }

    private void validerClient(Client client) {

        if (client.getNom() == null ||
                client.getNom().isBlank()) {
            throw new IllegalArgumentException(
                    "Le nom du client est obligatoire");
        }

        if (client.getPrenom() == null ||
                client.getPrenom().isBlank()) {
            throw new IllegalArgumentException(
                    "Le prénom du client est obligatoire");
        }

        if (client.getEmail() == null ||
                client.getEmail().isBlank()) {
            throw new IllegalArgumentException(
                    "L'email du client est obligatoire");
        }
    }
}
