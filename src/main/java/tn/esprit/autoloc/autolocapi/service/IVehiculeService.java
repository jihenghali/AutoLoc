package tn.esprit.autoloc.autolocapi.service;

import tn.esprit.autoloc.autolocapi.domain.Vehicule;

import java.util.List;
import java.util.Optional;

public interface IVehiculeService {

    Vehicule create(Vehicule vehicule);

    List<Vehicule> findAll();

    Optional<Vehicule> findById(Long id);

    Vehicule update(Long id, Vehicule vehicule);

    void deleteById(Long id);
}
