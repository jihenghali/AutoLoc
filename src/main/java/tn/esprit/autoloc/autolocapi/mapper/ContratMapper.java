package tn.esprit.autoloc.autolocapi.mapper;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.dto.ContratDTO;

@Component
public class ContratMapper {

    public ContratDTO toDTO(Contrat entity) {
        ContratDTO dto = new ContratDTO();
        dto.setIdContrat(entity.getIdContrat());
        dto.setDateSignature(entity.getDateSignature());
        dto.setMontantTotal(entity.getMontantTotal());
        dto.setValide(entity.getValide());
        if (entity.getReservation() != null) {
            dto.setReservationId(entity.getReservation().getIdReservation());
        }
        return dto;
    }

    public Contrat toEntity(ContratDTO dto) {
        Contrat entity = new Contrat();
        entity.setIdContrat(dto.getIdContrat());
        entity.setDateSignature(dto.getDateSignature());
        entity.setMontantTotal(dto.getMontantTotal());
        entity.setValide(dto.getValide());
        if (dto.getReservationId() != null) {
            Reservation reservation = new Reservation();
            reservation.setIdReservation(dto.getReservationId());
            entity.setReservation(reservation);
        }
        return entity;
    }
}
