package tn.esprit.autoloc.autolocapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import lombok.Data;
import tn.esprit.autoloc.autolocapi.domain.StatutReservation;

@Data
public class ReservationDTO {

    @Positive
    private Long idReservation;

    @NotNull
    private LocalDate dateDebut;

    @NotNull
    private LocalDate dateFin;

    private StatutReservation statut;

    @Positive
    private Long vehiculeId;

    @Positive
    private Long clientId;
}
