package tn.esprit.autoloc.autolocapi.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;

public interface IVehiculeRepository extends JpaRepository<Vehicule, Long> {

    @Query(value = "SELECT * FROM vehicule WHERE id_vehicule = :idVehicule FOR UPDATE", nativeQuery = true)
    Optional<Vehicule> findForUpdateByIdVehicule(Long idVehicule);

    Optional<Vehicule> findByImmatriculation(String immatriculation);
}
