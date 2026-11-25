package tn.esprit.autoloc.autolocapi.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.dto.VehiculeDTO;

@Component
public class VehiculeMapper {

    public VehiculeDTO toDTO(Vehicule vehicule) {

        VehiculeDTO dto = new VehiculeDTO();

        dto.setIdVehicule(vehicule.getIdVehicule());
        dto.setMarque(vehicule.getMarque());
        dto.setModele(vehicule.getModele());
        dto.setImmatriculation(vehicule.getImmatriculation());
        dto.setTarifJournalier(vehicule.getTarifJournalier());
        dto.setCategorie(vehicule.getCategorie());
        dto.setStatut(vehicule.getStatut());

        return dto;
    }

    public Vehicule toEntity(VehiculeDTO dto) {

        Vehicule vehicule = new Vehicule();

        vehicule.setMarque(dto.getMarque());
        vehicule.setModele(dto.getModele());
        vehicule.setImmatriculation(dto.getImmatriculation());
        vehicule.setTarifJournalier(dto.getTarifJournalier());
        vehicule.setCategorie(dto.getCategorie());
        vehicule.setStatut(dto.getStatut());

        return vehicule;
    }
}