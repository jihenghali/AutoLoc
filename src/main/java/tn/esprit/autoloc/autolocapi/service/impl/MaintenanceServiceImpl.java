
package tn.esprit.autoloc.autolocapi.service.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.autolocapi.domain.Maintenance;
import tn.esprit.autoloc.autolocapi.dto.MaintenanceDTO;
import tn.esprit.autoloc.autolocapi.mapper.MaintenanceMapper;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IMaintenanceRepository;
import tn.esprit.autoloc.autolocapi.service.IMaintenanceService;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;

    @Override
    public MaintenanceDTO create(MaintenanceDTO dto) {
        Maintenance maintenance = maintenanceMapper.toEntity(dto);

        if (maintenance.getIdMaintenance() != null) {
            throw new IllegalArgumentException(
                    "Une nouvelle maintenance ne doit pas avoir d'identifiant");
        }

        validerMaintenance(maintenance);

        return maintenanceMapper.toDTO(maintenanceRepository.save(maintenance));
    }

    @Override
    public MaintenanceDTO findById(Long id) {
        return maintenanceMapper.toDTO(findEntityById(id));
    }

    private Maintenance findEntityById(Long id) {

        return maintenanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Maintenance", id));
    }

    @Override
    public List<MaintenanceDTO> findAll() {

        return maintenanceRepository.findAll().stream()
                .map(maintenanceMapper::toDTO)
                .toList();
    }

    @Override
    public MaintenanceDTO update(Long id, MaintenanceDTO dto) {
        Maintenance maintenance = maintenanceMapper.toEntity(dto);

        Maintenance existante = findEntityById(id);

        validerMaintenance(maintenance);

        existante.setDateDebut(maintenance.getDateDebut());
        existante.setDateFin(maintenance.getDateFin());
        existante.setDescription(maintenance.getDescription());

        return maintenanceMapper.toDTO(maintenanceRepository.save(existante));
    }

    @Override
    public void deleteById(Long id) {

        if (!maintenanceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Maintenance", id);
        }

        maintenanceRepository.deleteById(id);
    }

    private void validerMaintenance(Maintenance maintenance) {

        if (maintenance.getDateDebut() == null ||
                maintenance.getDateFin() == null) {

            throw new IllegalArgumentException(
                    "Les dates de début et de fin sont obligatoires");
        }

        if (maintenance.getDateFin()
                .isBefore(maintenance.getDateDebut())) {

            throw new IllegalArgumentException(
                    "La date de fin ne peut pas être antérieure à la date de début");
        }
    }
}
