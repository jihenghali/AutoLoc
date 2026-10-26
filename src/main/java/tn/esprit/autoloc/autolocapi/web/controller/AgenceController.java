
package tn.esprit.autoloc.autolocapi.web.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import tn.esprit.autoloc.autolocapi.domain.Agence;
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
                    description = "Agence créée avec succès"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Données de l'agence invalides"
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Agence create(@RequestBody Agence agence) {
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
                    description = "Agence trouvée"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Agence introuvable"
            )
    })
    @GetMapping("/{id}")
    public Agence findById(
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
    public List<Agence> findAll() {
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
                    description = "Agence modifiée avec succès"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Données invalides"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Agence introuvable"
            )
    })
    @PutMapping("/{id}")
    public Agence update(
            @Parameter(description = "Identifiant de l'agence")
            @PathVariable Long id,
            @RequestBody Agence agence
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
                    description = "Agence supprimée avec succès"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Agence introuvable"
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
