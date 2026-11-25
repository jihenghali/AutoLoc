package tn.esprit.autoloc.autolocapi.dto;

import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import tn.esprit.autoloc.autolocapi.domain.ModePaiement;

@Data
public class PaiementDTO {

    @Positive
    private Long idPaiement;

    private BigDecimal montant;

    private LocalDate datePaiement;

    private ModePaiement modePaiement;

    @Positive
    private Long contratId;
}
