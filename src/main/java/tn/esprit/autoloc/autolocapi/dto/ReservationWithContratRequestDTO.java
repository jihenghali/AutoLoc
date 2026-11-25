package tn.esprit.autoloc.autolocapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservationWithContratRequestDTO(
        @NotNull LocalDate dateDebut,
        @NotNull LocalDate dateFin,
        @NotNull @Positive Long clientId,
        @NotNull @Positive Long vehiculeId,
        @NotNull LocalDate dateSignature,
        @NotNull @PositiveOrZero BigDecimal montantTotal) {
}
