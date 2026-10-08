package tn.esprit.autoloc.autolocapi.service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.repository.IVehiculeRepository;
import tn.esprit.autoloc.autolocapi.service.IVehiculeService;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculeService implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;

    public VehiculeService(IVehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    @Override
    public Vehicule create(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Optional<Vehicule> findById(Long id) {
        return vehiculeRepository.findById(id);
    }

    @Override
    public Vehicule update(Long id, Vehicule vehicule) {
        vehicule.setIdVehicule(id);
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public void deleteById(Long id) {
        vehiculeRepository.deleteById(id);
    }
}
