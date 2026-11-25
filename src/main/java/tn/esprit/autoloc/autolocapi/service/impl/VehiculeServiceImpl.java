package tn.esprit.autoloc.autolocapi.service.impl;

import org.springframework.transaction.annotation.Transactional;
import static tn.esprit.autoloc.autolocapi.service.RechercheAffectation.resultatUnique;
import tn.esprit.autoloc.autolocapi.domain.Equipement;
import tn.esprit.autoloc.autolocapi.domain.Maintenance;
import tn.esprit.autoloc.autolocapi.repository.IEquipementRepository;
import tn.esprit.autoloc.autolocapi.repository.IMaintenanceRepository;

import java.math.BigDecimal;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.dto.VehiculeDTO;
import tn.esprit.autoloc.autolocapi.mapper.VehiculeMapper;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IVehiculeRepository;
import tn.esprit.autoloc.autolocapi.service.IVehiculeService;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;
    private final VehiculeMapper vehiculeMapper;

    private final IEquipementRepository equipementRepository;
    private final IMaintenanceRepository maintenanceRepository;

    @Override
    public VehiculeDTO create(VehiculeDTO dto) {

        if (dto.getIdVehicule() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau véhicule ne doit pas avoir d'identifiant");
        }

        Vehicule vehicule = vehiculeMapper.toEntity(dto);

        validerVehicule(vehicule);

        return vehiculeMapper.toDTO(
                vehiculeRepository.save(vehicule));
    }

    @Override
    public VehiculeDTO findById(Long id) {

        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vehicule", id));

        return vehiculeMapper.toDTO(vehicule);
    }

    @Override
    public List<VehiculeDTO> findAll() {

        return vehiculeRepository.findAll()
                .stream()
                .map(vehiculeMapper::toDTO)
                .toList();
    }

    @Override
    public VehiculeDTO update(Long id, VehiculeDTO dto) {

        Vehicule existant = vehiculeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vehicule", id));

        Vehicule vehicule = vehiculeMapper.toEntity(dto);

        validerVehicule(vehicule);

        existant.setImmatriculation(vehicule.getImmatriculation());
        existant.setMarque(vehicule.getMarque());
        existant.setModele(vehicule.getModele());
        existant.setCategorie(vehicule.getCategorie());
        existant.setTarifJournalier(vehicule.getTarifJournalier());
        existant.setStatut(vehicule.getStatut());

        return vehiculeMapper.toDTO(
                vehiculeRepository.save(existant));
    }

    @Override
    public void deleteById(Long id) {

        if (!vehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicule", id);
        }

        vehiculeRepository.deleteById(id);
    }

    private void validerVehicule(Vehicule vehicule) {

        if (vehicule.getTarifJournalier() == null ||
                vehicule.getTarifJournalier().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Le tarif journalier doit être positif ou nul");
        }

        if (vehicule.getImmatriculation() == null ||
                vehicule.getImmatriculation().isBlank()) {
            throw new IllegalArgumentException(
                    "L'immatriculation est obligatoire");
        }

        if (vehicule.getMarque() == null ||
                vehicule.getMarque().isBlank()) {
            throw new IllegalArgumentException(
                    "La marque est obligatoire");
        }

        if (vehicule.getModele() == null ||
                vehicule.getModele().isBlank()) {
            throw new IllegalArgumentException(
                    "Le modèle est obligatoire");
        }

        if (vehicule.getCategorie() == null ||
                vehicule.getStatut() == null) {
            throw new IllegalArgumentException(
                    "La catégorie et le statut sont obligatoires");
        }
    }

    @Override
    @Transactional
    public VehiculeDTO affecterVehiculeAEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = vehiculeRepository.findForUpdateByIdVehicule(idVehicule)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", idVehicule));
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", idEquipement));

        if (!vehicule.getEquipements().contains(equipement)) {
            vehicule.getEquipements().add(equipement);
        }
        return vehiculeMapper.toDTO(vehicule);
    }

    @Override
    @Transactional
    public VehiculeDTO affecterMaintenanceAvehicule(String description, String immatriculation) {
        if (description == null || description.isBlank() || immatriculation == null || immatriculation.isBlank()) {
            throw new IllegalArgumentException("La description et l'immatriculation sont obligatoires");
        }

        Vehicule vehicule = vehiculeRepository.findByImmatriculation(immatriculation.trim())
                .orElseThrow(() -> new ResourceNotFoundException("V?hicule introuvable pour cette immatriculation"));
        Maintenance maintenance = resultatUnique(maintenanceRepository.findTop2ByDescription(description.trim()),
                "la description de la maintenance");
        maintenance.setVehicule(vehicule);
        return vehiculeMapper.toDTO(vehicule);
    }
}
