
package tn.esprit.autoloc.autolocapi.web.controller;

import tn.esprit.autoloc.autolocapi.dto.ReservationResponseDTO;
import tn.esprit.autoloc.autolocapi.dto.ReservationWithContratRequestDTO;
import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc.autolocapi.dto.ReservationDTO;
import tn.esprit.autoloc.autolocapi.service.IReservationService;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;

    @PostMapping
    public ReservationDTO create(
            @Valid @RequestBody ReservationDTO reservation) {
        return reservationService.create(reservation);
    }

    @GetMapping("/{id}")
    public ReservationDTO findById(@PathVariable Long id) {
        return reservationService.findById(id);
    }

    @GetMapping
    public List<ReservationDTO> findAll() {
        return reservationService.findAll();
    }

    @PutMapping("/{id}")
    public ReservationDTO update(
            @PathVariable Long id,
            @Valid @RequestBody ReservationDTO reservation) {
        return reservationService.update(id, reservation);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        reservationService.deleteById(id);
    }

    @PutMapping("/{idReservation}/client")
    public ReservationResponseDTO affecterReservationAClient(@PathVariable Long idReservation,
                                                             @RequestParam String nom, @RequestParam String prenom) {
        return reservationService.affecterReservationAClient(idReservation, nom, prenom);
    }

    @PostMapping("/avec-contrat")
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponseDTO creerReservationEtContratAssocie(
            @Valid @RequestBody ReservationWithContratRequestDTO requestDTO) {
        return reservationService.creerReservationEtContratAssocie(requestDTO);
    }
}
