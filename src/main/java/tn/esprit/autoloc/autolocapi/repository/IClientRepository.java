package tn.esprit.autoloc.autolocapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.autolocapi.domain.Client;

public interface IClientRepository extends JpaRepository<Client, Long> {

    List<Client> findTop2ByNomIgnoreCaseAndPrenomIgnoreCase(String nom, String prenom);
}
