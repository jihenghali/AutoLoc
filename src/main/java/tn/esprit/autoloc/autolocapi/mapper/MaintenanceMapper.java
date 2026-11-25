package tn.esprit.autoloc.autolocapi.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Maintenance;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.dto.MaintenanceDTO;

@Component
public class MaintenanceMapper {

    public MaintenanceDTO toDTO(Maintenance entity) {
        MaintenanceDTO dto = new MaintenanceDTO();
        dto.setIdMaintenance(entity.getIdMaintenance());
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        dto.setDescription(entity.getDescription());
        if (entity.getVehicule() != null) {
            dto.setVehiculeId(entity.getVehicule().getIdVehicule());
        }
        return dto;
    }

    public Maintenance toEntity(MaintenanceDTO dto) {
        Maintenance entity = new Maintenance();
        entity.setIdMaintenance(dto.getIdMaintenance());
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        entity.setDescription(dto.getDescription());
        if (dto.getVehiculeId() != null) {
            Vehicule vehicule = new Vehicule();
            vehicule.setIdVehicule(dto.getVehiculeId());
            entity.setVehicule(vehicule);
        }
        return entity;
    }
}
