package tn.esprit.autoloc.autolocapi.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Paiement;
import tn.esprit.autoloc.autolocapi.dto.PaiementDTO;

@Component
public class PaiementMapper {

    public PaiementDTO toDTO(Paiement entity) {
        PaiementDTO dto = new PaiementDTO();
        dto.setIdPaiement(entity.getIdPaiement());
        dto.setMontant(entity.getMontant());
        dto.setDatePaiement(entity.getDatePaiement());
        dto.setModePaiement(entity.getModePaiement());
        if (entity.getContrat() != null) {
            dto.setContratId(entity.getContrat().getIdContrat());
        }
        return dto;
    }
}
