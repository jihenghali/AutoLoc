
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

import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.service.IClientService;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Tag(
        name = "Clients",
        description = "Gestion des clients AutoLoc"
)
public class ClientController {

    private final IClientService clientService;

    // 1. CREATE
    @Operation(summary = "Ajouter un client")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Client créé avec succès"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Données du client invalides"
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client) {
        return clientService.create(client);
    }

    // 2. READ - Par identifiant
    @Operation(summary = "Consulter un client par ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Client trouvé"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Client introuvable"
            )
    })
    @GetMapping("/{id}")
    public Client findById(
            @Parameter(description = "Identifiant du client")
            @PathVariable Long id
    ) {
        return clientService.findById(id);
    }

    // 3. READ - Tous les clients
    @Operation(summary = "Lister tous les clients")
    @ApiResponse(
            responseCode = "200",
            description = "Liste des clients récupérée"
    )
    @GetMapping
    public List<Client> findAll() {
        return clientService.findAll();
    }

    // 4. UPDATE
    @Operation(summary = "Modifier un client")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Client modifié avec succès"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Données invalides"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Client introuvable"
            )
    })
    @PutMapping("/{id}")
    public Client update(
            @Parameter(description = "Identifiant du client")
            @PathVariable Long id,
            @RequestBody Client client
    ) {
        return clientService.update(id, client);
    }

    // 5. DELETE
    @Operation(summary = "Supprimer un client")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Client supprimé avec succès"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Client introuvable"
            )
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(
            @Parameter(description = "Identifiant du client")
            @PathVariable Long id
    ) {
        clientService.deleteById(id);
    }
}
