package tn.esprit.autoloc.autolocapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import tn.esprit.autoloc.autolocapi.domain.RoleEmploye;

@Data
public class EmployeDTO {

    @Positive
    private Long idEmploye;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @NotNull
    private RoleEmploye role;

    @Positive
    private Long agenceId;
}
