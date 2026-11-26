package tn.esprit.autoloc.autolocapi.repository;

import java.util.List;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.autolocapi.domain.Contrat;

public interface IContratRepository extends JpaRepository<Contrat, Long> {

    @Query(value = "SELECT * FROM contrat WHERE date_signature = :dateSignature LIMIT 2 FOR UPDATE", nativeQuery = true)
    List<Contrat> findTop2ByDateSignature(LocalDate dateSignature);

    boolean existsByReservationIdReservation(Long idReservation);

    List<Contrat> findByValideTrueAndReservationDateFinBetween(LocalDate debut, LocalDate fin);
}
