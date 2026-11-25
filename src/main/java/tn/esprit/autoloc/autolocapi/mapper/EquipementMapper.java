package tn.esprit.autoloc.autolocapi.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Equipement;
import tn.esprit.autoloc.autolocapi.dto.EquipementDTO;

@Component
public class EquipementMapper {

    public EquipementDTO toDTO(Equipement entity) {
        EquipementDTO dto = new EquipementDTO();
        dto.setIdEquipement(entity.getIdEquipement());
        dto.setLibelle(entity.getLibelle());
        return dto;
    }

    public Equipement toEntity(EquipementDTO dto) {
        Equipement entity = new Equipement();
        entity.setIdEquipement(dto.getIdEquipement());
        entity.setLibelle(dto.getLibelle());
        return entity;
    }
}
