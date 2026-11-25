package tn.esprit.autoloc.autolocapi.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.dto.EmployeDTO;

@Component
public class EmployeMapper {

    public EmployeDTO toDTO(Employe entity) {
        EmployeDTO dto = new EmployeDTO();
        dto.setIdEmploye(entity.getIdEmploye());
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        dto.setRole(entity.getRole());
        if (entity.getAgence() != null) {
            dto.setAgenceId(entity.getAgence().getIdAgence());
        }
        return dto;
    }

    public Employe toEntity(EmployeDTO dto) {
        Employe entity = new Employe();
        entity.setIdEmploye(dto.getIdEmploye());
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setRole(dto.getRole());
        if (dto.getAgenceId() != null) {
            Agence agence = new Agence();
            agence.setIdAgence(dto.getAgenceId());
            entity.setAgence(agence);
        }
        return entity;
    }
}
