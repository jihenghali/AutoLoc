package tn.esprit.autoloc.autolocapi.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.dto.ClientDTO;

@Component
public class ClientMapper {

    public ClientDTO toDTO(Client entity) {
        ClientDTO dto = new ClientDTO();
        dto.setIdClient(entity.getIdClient());
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setEmail(entity.getEmail());
        dto.setTelephone(entity.getTelephone());
        dto.setNumPermis(entity.getNumPermis());
        dto.setDateInscription(entity.getDateInscription());
        return dto;
    }

    public Client toEntity(ClientDTO dto) {
        Client entity = new Client();
        entity.setIdClient(dto.getIdClient());
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setEmail(dto.getEmail());
        entity.setTelephone(dto.getTelephone());
        entity.setNumPermis(dto.getNumPermis());
        entity.setDateInscription(dto.getDateInscription());
        return entity;
    }
}
