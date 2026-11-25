package tn.esprit.autoloc.autolocapi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.autolocapi.domain.Maintenance;

public interface IMaintenanceRepository extends JpaRepository<Maintenance, Long> {

    @Query(value = "SELECT * FROM maintenance WHERE description = :description LIMIT 2 FOR UPDATE", nativeQuery = true)
    List<Maintenance> findTop2ByDescription(String description);
}
