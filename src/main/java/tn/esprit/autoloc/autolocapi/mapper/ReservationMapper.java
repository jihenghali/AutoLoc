package tn.esprit.autoloc.autolocapi.mapper;

import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.dto.ReservationResponseDTO;

import org.springframework.stereotype.Component;
import tn.esprit.autoloc.autolocapi.domain.Client;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.dto.ReservationDTO;

@Component
public class ReservationMapper {

    public ReservationDTO toDTO(Reservation entity) {
        ReservationDTO dto = new ReservationDTO();
        dto.setIdReservation(entity.getIdReservation());
        dto.setDateDebut(entity.getDateDebut());
        dto.setDateFin(entity.getDateFin());
        dto.setStatut(entity.getStatut());
        if (entity.getVehicule() != null) {
            dto.setVehiculeId(entity.getVehicule().getIdVehicule());
        }
        if (entity.getClient() != null) {
            dto.setClientId(entity.getClient().getIdClient());
        }
        return dto;
    }

    public Reservation toEntity(ReservationDTO dto) {
        Reservation entity = new Reservation();
        entity.setIdReservation(dto.getIdReservation());
        entity.setDateDebut(dto.getDateDebut());
        entity.setDateFin(dto.getDateFin());
        entity.setStatut(dto.getStatut());
        if (dto.getVehiculeId() != null) {
            Vehicule vehicule = new Vehicule();
            vehicule.setIdVehicule(dto.getVehiculeId());
            entity.setVehicule(vehicule);
        }
        if (dto.getClientId() != null) {
            Client client = new Client();
            client.setIdClient(dto.getClientId());
            entity.setClient(client);
        }
        return entity;
    }

    public ReservationResponseDTO toResponseDTO(Reservation reservation) {
        return toResponseDTO(reservation, reservation.getContrat());
    }

    public ReservationResponseDTO toResponseDTO(Reservation reservation,
                                                Contrat contrat) {
        ReservationDTO dto = toDTO(reservation);
        return new ReservationResponseDTO(dto.getIdReservation(), dto.getDateDebut(), dto.getDateFin(),
                dto.getStatut(), dto.getClientId(), dto.getVehiculeId(),
                contrat == null ? null : contrat.getIdContrat());
    }
}
