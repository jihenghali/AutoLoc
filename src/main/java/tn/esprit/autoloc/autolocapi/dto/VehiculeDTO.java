package tn.esprit.autoloc.autolocapi.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;
import tn.esprit.autoloc.autolocapi.domain.CategorieVehicule;
import tn.esprit.autoloc.autolocapi.domain.StatutVehicule;

@Data
public class VehiculeDTO {

    @Positive
    private Long idVehicule;

    @NotBlank
    @Size(max = 20)
    private String immatriculation;

    @NotBlank
    @Size(max = 50)
    private String marque;

    @NotBlank
    @Size(max = 50)
    private String modele;

    @NotNull
    private CategorieVehicule categorie;

    @NotNull
    @PositiveOrZero
    @Digits(integer = 8, fraction = 2)
    private BigDecimal tarifJournalier;

    @NotNull
    private StatutVehicule statut;
}
