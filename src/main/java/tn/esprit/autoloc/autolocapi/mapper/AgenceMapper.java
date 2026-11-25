package tn.esprit.autoloc.autolocapi.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.dto.AgenceDTO;

@Component
public class AgenceMapper {

    public AgenceDTO toDTO(Agence entity) {
        AgenceDTO dto = new AgenceDTO();
        dto.setIdAgence(entity.getIdAgence());
        dto.setNom(entity.getNom());
        dto.setVille(entity.getVille());
        dto.setAdresse(entity.getAdresse());
        return dto;
    }

    public Agence toEntity(AgenceDTO dto) {
        Agence entity = new Agence();
        entity.setIdAgence(dto.getIdAgence());
        entity.setNom(dto.getNom());
        entity.setVille(dto.getVille());
        entity.setAdresse(dto.getAdresse());
        return entity;
    }
}
