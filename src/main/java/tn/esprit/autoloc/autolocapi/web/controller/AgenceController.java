
package tn.esprit.autoloc.autolocapi.web.controller;

import jakarta.validation.Valid;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import tn.esprit.autoloc.autolocapi.dto.AgenceDTO;
import tn.esprit.autoloc.autolocapi.service.IAgenceService;

@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
@Tag(
        name = "Agences",
        description = "Gestion des agences AutoLoc"
)
public class AgenceController {

    private final IAgenceService agenceService;

    // 1. CREATE - Ajouter une agence
    @Operation(
            summary = "Ajouter une agence",
            description = "Créer une nouvelle agence AutoLoc"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "AgenceDTO créée avec succès"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Données de l'agence invalides"
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgenceDTO create(@Valid @RequestBody AgenceDTO agence) {
        return agenceService.create(agence);
    }

    // 2. READ - Consulter une agence par ID
    @Operation(
            summary = "Consulter une agence",
            description = "Récupérer une agence par son identifiant"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "AgenceDTO trouvée"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "AgenceDTO introuvable"
            )
    })
    @GetMapping("/{id}")
    public AgenceDTO findById(
            @Parameter(description = "Identifiant de l'agence")
            @PathVariable Long id
    ) {
        return agenceService.findById(id);
    }

    // 3. READ - Lister toutes les agences
    @Operation(
            summary = "Lister toutes les agences",
            description = "Récupérer la liste des agences AutoLoc"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des agences récupérée"
    )
    @GetMapping
    public List<AgenceDTO> findAll() {
        return agenceService.findAll();
    }

    // 4. UPDATE - Modifier une agence
    @Operation(
            summary = "Modifier une agence",
            description = "Mettre à jour une agence existante"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "AgenceDTO modifiée avec succès"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Données invalides"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "AgenceDTO introuvable"
            )
    })
    @PutMapping("/{id}")
    public AgenceDTO update(
            @Parameter(description = "Identifiant de l'agence")
            @PathVariable Long id,
            @Valid @RequestBody AgenceDTO agence
    ) {
        return agenceService.update(id, agence);
    }

    // 5. DELETE - Supprimer une agence
    @Operation(
            summary = "Supprimer une agence",
            description = "Supprimer une agence par son identifiant"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "AgenceDTO supprimée avec succès"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "AgenceDTO introuvable"
            )
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(
            @Parameter(description = "Identifiant de l'agence")
            @PathVariable Long id
    ) {
        agenceService.deleteById(id);
    }
}
