package tn.esprit.autoloc.autolocapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AgenceDTO {

    @Positive
    private Long idAgence;

    @NotBlank
    private String nom;

    private String ville;

    private String adresse;
}
