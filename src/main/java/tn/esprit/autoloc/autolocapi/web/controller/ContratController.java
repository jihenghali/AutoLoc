
package tn.esprit.autoloc.autolocapi.web.controller;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc.autolocapi.dto.ContratDTO;
import tn.esprit.autoloc.autolocapi.service.IContratService;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
public class ContratController {

    private final IContratService contratService;

    @PostMapping
    public ContratDTO create(@Valid @RequestBody ContratDTO contrat) {
        return contratService.create(contrat);
    }

    @GetMapping("/{id}")
    public ContratDTO findById(@PathVariable Long id) {
        return contratService.findById(id);
    }

    @GetMapping
    public List<ContratDTO> findAll() {
        return contratService.findAll();
    }

    @PutMapping("/{id}")
    public ContratDTO update(@PathVariable Long id,
                          @Valid @RequestBody ContratDTO contrat) {
        return contratService.update(id, contrat);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        contratService.deleteById(id);
    }

    @PutMapping("/reservation")
    public ContratDTO affecterContratAReservation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateSignature,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {
        return contratService.affecterContratAReservation(dateSignature, dateDebut, dateFin);
    }
}
