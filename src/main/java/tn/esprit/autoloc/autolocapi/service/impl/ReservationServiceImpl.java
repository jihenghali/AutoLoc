
package tn.esprit.autoloc.autolocapi.service.impl;

import org.springframework.transaction.annotation.Transactional;
import static tn.esprit.autoloc.autolocapi.service.RechercheAffectation.resultatUnique;
import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.domain.StatutReservation;
import tn.esprit.autoloc.autolocapi.dto.ReservationResponseDTO;
import tn.esprit.autoloc.autolocapi.dto.ReservationWithContratRequestDTO;
import tn.esprit.autoloc.autolocapi.repository.IClientRepository;
import tn.esprit.autoloc.autolocapi.repository.IContratRepository;
import tn.esprit.autoloc.autolocapi.repository.IVehiculeRepository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.dto.ReservationDTO;
import tn.esprit.autoloc.autolocapi.mapper.ReservationMapper;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IReservationRepository;
import tn.esprit.autoloc.autolocapi.service.IReservationService;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    private final IClientRepository clientRepository;
    private final IContratRepository contratRepository;
    private final IVehiculeRepository vehiculeRepository;

    @Override
    public ReservationDTO create(ReservationDTO dto) {
        Reservation reservation = reservationMapper.toEntity(dto);

        if (reservation.getIdReservation() != null) {
            throw new IllegalArgumentException(
                    "Une nouvelle réservation ne doit pas avoir d'identifiant");
        }

        validerReservation(reservation);

        return reservationMapper.toDTO(reservationRepository.save(reservation));
    }

    @Override
    public ReservationDTO findById(Long id) {
        return reservationMapper.toDTO(findEntityById(id));
    }

    private Reservation findEntityById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reservation", id));
    }

    @Override
    public List<ReservationDTO> findAll() {
        return reservationRepository.findAll().stream()
                .map(reservationMapper::toDTO)
                .toList();
    }

    @Override
    public ReservationDTO update(Long id, ReservationDTO dto) {
        Reservation reservation = reservationMapper.toEntity(dto);

        Reservation existante = findEntityById(id);

        validerReservation(reservation);

        existante.setDateDebut(reservation.getDateDebut());
        existante.setDateFin(reservation.getDateFin());
        existante.setStatut(reservation.getStatut());

        return reservationMapper.toDTO(reservationRepository.save(existante));
    }

    @Override
    public void deleteById(Long id) {

        if (!reservationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reservation", id);
        }

        reservationRepository.deleteById(id);
    }

    private void validerReservation(Reservation reservation) {

        if (reservation.getDateDebut() == null ||
                reservation.getDateFin() == null) {
            throw new IllegalArgumentException(
                    "Les dates de début et de fin sont obligatoires");
        }

        if (reservation.getDateFin()
                .isBefore(reservation.getDateDebut())) {
            throw new IllegalArgumentException(
                    "La date de fin ne peut pas être antérieure à la date de début");
        }
    }

    @Override
    @Transactional
    public ReservationResponseDTO affecterReservationAClient(Long idReservation, String nom, String prenom) {
        if (nom == null || nom.isBlank() || prenom == null || prenom.isBlank()) {
            throw new IllegalArgumentException("Le nom et le pr?nom sont obligatoires");
        }

        Reservation reservation = findEntityById(idReservation);
        Client client = resultatUnique(
                clientRepository.findTop2ByNomIgnoreCaseAndPrenomIgnoreCase(nom.trim(), prenom.trim()),
                "le nom et le pr?nom du client");
        reservation.setClient(client);
        return reservationMapper.toResponseDTO(reservation);
    }

    @Override
    @Transactional
    public ReservationResponseDTO creerReservationEtContratAssocie(ReservationWithContratRequestDTO requestDTO) {
        Reservation reservation = new Reservation();
        reservation.setDateDebut(requestDTO.dateDebut());
        reservation.setDateFin(requestDTO.dateFin());
        reservation.setStatut(StatutReservation.EN_ATTENTE);
        validerReservation(reservation);

        if (requestDTO.dateSignature() == null || requestDTO.montantTotal() == null
                || requestDTO.montantTotal().signum() < 0) {
            throw new IllegalArgumentException("La date de signature et un montant positif ou nul sont obligatoires");
        }

        Client client = clientRepository.findById(requestDTO.clientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client", requestDTO.clientId()));
        Vehicule vehicule = vehiculeRepository.findById(requestDTO.vehiculeId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", requestDTO.vehiculeId()));
        reservation.setClient(client);
        reservation.setVehicule(vehicule);
        Reservation enregistree = reservationRepository.save(reservation);

        Contrat contrat = new Contrat();
        contrat.setDateSignature(requestDTO.dateSignature());
        contrat.setMontantTotal(requestDTO.montantTotal());
        contrat.setValide(false);
        contrat.setReservation(enregistree);
        return reservationMapper.toResponseDTO(enregistree, contratRepository.save(contrat));
    }
}
