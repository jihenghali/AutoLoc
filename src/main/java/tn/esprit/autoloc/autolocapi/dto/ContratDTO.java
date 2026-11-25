package tn.esprit.autoloc.autolocapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class ContratDTO {

    @Positive
    private Long idContrat;

    private LocalDate dateSignature;

    @NotNull
    @PositiveOrZero
    private BigDecimal montantTotal;

    private Boolean valide;

    @Positive
    private Long reservationId;
}
