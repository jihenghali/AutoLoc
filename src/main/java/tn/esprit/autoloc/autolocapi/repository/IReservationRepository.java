package tn.esprit.autoloc.autolocapi.repository;

import java.util.List;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.autolocapi.domain.Reservation;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM reservation WHERE date_debut = :dateDebut AND date_fin = :dateFin LIMIT 2 FOR UPDATE", nativeQuery = true)
    List<Reservation> findTop2ByDateDebutAndDateFin(LocalDate dateDebut, LocalDate dateFin);
}
