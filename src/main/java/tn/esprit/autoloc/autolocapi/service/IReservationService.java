
package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.dto.ReservationResponseDTO;
import tn.esprit.autoloc.autolocapi.dto.ReservationWithContratRequestDTO;

import java.util.List;
import tn.esprit.autoloc.autolocapi.dto.ReservationDTO;

public interface IReservationService {

    ReservationDTO create(ReservationDTO reservation);

    ReservationDTO findById(Long id);

    List<ReservationDTO> findAll();

    ReservationDTO update(Long id, ReservationDTO reservation);

    void deleteById(Long id);

    ReservationResponseDTO affecterReservationAClient(Long idReservation, String nom, String prenom);

    ReservationResponseDTO creerReservationEtContratAssocie(ReservationWithContratRequestDTO requestDTO);
}
