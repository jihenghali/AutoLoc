package tn.esprit.autoloc.autolocapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EquipementDTO {

    @Positive
    private Long idEquipement;

    @NotBlank
    private String libelle;
}
