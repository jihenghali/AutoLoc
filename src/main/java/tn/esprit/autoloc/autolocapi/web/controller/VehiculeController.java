
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

import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.service.IVehiculeService;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
@Tag(
        name = "Véhicules",
        description = "Gestion des véhicules de location AutoLoc"
)
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    // 1. CREATE - Ajouter un véhicule
    @Operation(
            summary = "Ajouter un véhicule",
            description = "Créer un nouveau véhicule dans la base de données"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Véhicule créé avec succès"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Données du véhicule invalides"
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicule create(@RequestBody Vehicule vehicule) {
        return vehiculeService.create(vehicule);
    }

    // 2. READ - Consulter un véhicule par ID
    @Operation(
            summary = "Consulter un véhicule",
            description = "Récupérer un véhicule à partir de son identifiant"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Véhicule trouvé"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Véhicule introuvable"
            )
    })
    @GetMapping("/{id}")
    public Vehicule findById(
            @Parameter(description = "Identifiant du véhicule")
            @PathVariable Long id
    ) {
        return vehiculeService.findById(id);
    }

    // 3. READ - Lister tous les véhicules
    @Operation(
            summary = "Lister les véhicules",
            description = "Récupérer tous les véhicules de la base de données"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des véhicules récupérée avec succès"
    )
    @GetMapping
    public List<Vehicule> findAll() {
        return vehiculeService.findAll();
    }

    // 4. UPDATE - Modifier un véhicule
    @Operation(
            summary = "Modifier un véhicule",
            description = "Mettre à jour les informations d'un véhicule existant"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Véhicule modifié avec succès"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Données invalides"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Véhicule introuvable"
            )
    })
    @PutMapping("/{id}")
    public Vehicule update(
            @Parameter(description = "Identifiant du véhicule")
            @PathVariable Long id,
            @RequestBody Vehicule vehicule
    ) {
        return vehiculeService.update(id, vehicule);
    }

    // 5. DELETE - Supprimer un véhicule
    @Operation(
            summary = "Supprimer un véhicule",
            description = "Supprimer un véhicule à partir de son identifiant"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Véhicule supprimé avec succès"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Véhicule introuvable"
            )
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(
            @Parameter(description = "Identifiant du véhicule")
            @PathVariable Long id
    ) {
        vehiculeService.deleteById(id);
    }
}
