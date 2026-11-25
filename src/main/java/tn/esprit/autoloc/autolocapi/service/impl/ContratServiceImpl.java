
package tn.esprit.autoloc.autolocapi.service.impl;

import org.springframework.transaction.annotation.Transactional;
import static tn.esprit.autoloc.autolocapi.service.RechercheAffectation.resultatUnique;
import java.time.LocalDate;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.repository.IReservationRepository;
import tn.esprit.autoloc.autolocapi.exception.AffectationConflictException;

import java.math.BigDecimal;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.dto.ContratDTO;
import tn.esprit.autoloc.autolocapi.mapper.ContratMapper;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IContratRepository;
import tn.esprit.autoloc.autolocapi.service.IContratService;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;
    private final ContratMapper contratMapper;

    private final IReservationRepository reservationRepository;

    @Override
    public ContratDTO create(ContratDTO dto) {
        Contrat contrat = contratMapper.toEntity(dto);

        if (contrat.getIdContrat() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau contrat ne doit pas avoir d'identifiant");
        }

        validerContrat(contrat);

        return contratMapper.toDTO(contratRepository.save(contrat));
    }

    @Override
    public ContratDTO findById(Long id) {
        return contratMapper.toDTO(findEntityById(id));
    }

    private Contrat findEntityById(Long id) {

        return contratRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contrat", id));
    }

    @Override
    public List<ContratDTO> findAll() {

        return contratRepository.findAll().stream()
                .map(contratMapper::toDTO)
                .toList();
    }

    @Override
    public ContratDTO update(Long id, ContratDTO dto) {
        Contrat contrat = contratMapper.toEntity(dto);

        Contrat existant = findEntityById(id);

        validerContrat(contrat);

        existant.setDateSignature(contrat.getDateSignature());
        existant.setMontantTotal(contrat.getMontantTotal());
        existant.setValide(contrat.getValide());

        return contratMapper.toDTO(contratRepository.save(existant));
    }

    @Override
    public void deleteById(Long id) {

        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat", id);
        }

        contratRepository.deleteById(id);
    }

    private void validerContrat(Contrat contrat) {

        if (contrat.getMontantTotal() == null ||
                contrat.getMontantTotal().compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Le montant total doit être positif ou nul");
        }
    }

    @Override
    @Transactional
    public ContratDTO affecterContratAReservation(LocalDate dateSignature, LocalDate dateDebut, LocalDate dateFin) {
        if (dateSignature == null || dateDebut == null || dateFin == null || dateFin.isBefore(dateDebut)) {
            throw new IllegalArgumentException("Les dates sont obligatoires et la fin doit suivre le d?but");
        }

        Contrat contrat = resultatUnique(contratRepository.findTop2ByDateSignature(dateSignature),
                "la date de signature du contrat");
        Reservation reservation = resultatUnique(
                reservationRepository.findTop2ByDateDebutAndDateFin(dateDebut, dateFin),
                "les dates de la r?servation");

        if (contrat.getReservation() != null) {
            if (!contrat.getReservation().getIdReservation().equals(reservation.getIdReservation())) {
                throw new AffectationConflictException("Le contrat est d?j? affect? ? une autre r?servation");
            }
        } else {
            if (contratRepository.existsByReservationIdReservation(reservation.getIdReservation())) {
                throw new AffectationConflictException("La r?servation poss?de d?j? un contrat");
            }
            contrat.setReservation(reservation);
        }
        return contratMapper.toDTO(contrat);
    }
}
