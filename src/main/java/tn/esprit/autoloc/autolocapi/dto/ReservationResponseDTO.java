package tn.esprit.autoloc.autolocapi.dto;

import java.time.LocalDate;
import tn.esprit.autoloc.autolocapi.domain.StatutReservation;

public record ReservationResponseDTO(
        Long idReservation, LocalDate dateDebut, LocalDate dateFin,
        StatutReservation statut, Long clientId, Long vehiculeId, Long contratId) {
}
