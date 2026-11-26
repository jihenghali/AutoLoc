package tn.esprit.autoloc.autolocapi.repository;

import java.util.List;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.domain.StatutReservation;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import org.springframework.data.jpa.repository.Lock;
import jakarta.persistence.LockModeType;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM reservation WHERE date_debut = :dateDebut AND date_fin = :dateFin LIMIT 2 FOR UPDATE", nativeQuery = true)
    List<Reservation> findTop2ByDateDebutAndDateFin(LocalDate dateDebut, LocalDate dateFin);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Reservation> findByStatutAndDateFinBefore(StatutReservation statut, LocalDate date);

    boolean existsByVehiculeAndStatutAndDateDebutLessThanEqualAndDateFinGreaterThanEqual(
            Vehicule vehicule, StatutReservation statut, LocalDate dateDebut, LocalDate dateFin);
}
