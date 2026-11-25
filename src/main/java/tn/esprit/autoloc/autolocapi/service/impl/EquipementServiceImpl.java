
package tn.esprit.autoloc.autolocapi.service.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.autolocapi.domain.Equipement;
import tn.esprit.autoloc.autolocapi.dto.EquipementDTO;
import tn.esprit.autoloc.autolocapi.mapper.EquipementMapper;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IEquipementRepository;
import tn.esprit.autoloc.autolocapi.service.IEquipementService;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements IEquipementService {

    private final IEquipementRepository equipementRepository;
    private final EquipementMapper equipementMapper;

    @Override
    public EquipementDTO create(EquipementDTO dto) {
        Equipement equipement = equipementMapper.toEntity(dto);

        if (equipement.getIdEquipement() != null) {
            throw new IllegalArgumentException(
                    "Un nouvel équipement ne doit pas avoir d'identifiant");
        }

        validerEquipement(equipement);

        return equipementMapper.toDTO(equipementRepository.save(equipement));
    }

    @Override
    public EquipementDTO findById(Long id) {
        return equipementMapper.toDTO(findEntityById(id));
    }

    private Equipement findEntityById(Long id) {
        return equipementRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Equipement", id));
    }

    @Override
    public List<EquipementDTO> findAll() {
        return equipementRepository.findAll().stream()
                .map(equipementMapper::toDTO)
                .toList();
    }

    @Override
    public EquipementDTO update(Long id, EquipementDTO dto) {
        Equipement equipement = equipementMapper.toEntity(dto);

        Equipement existant = findEntityById(id);

        validerEquipement(equipement);

        existant.setLibelle(equipement.getLibelle());

        return equipementMapper.toDTO(equipementRepository.save(existant));
    }

    @Override
    public void deleteById(Long id) {

        if (!equipementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Equipement", id);
        }

        equipementRepository.deleteById(id);
    }

    private void validerEquipement(Equipement equipement) {

        if (equipement.getLibelle() == null ||
                equipement.getLibelle().isBlank()) {
            throw new IllegalArgumentException(
                    "Le libellé de l'équipement est obligatoire");
        }
    }
}
