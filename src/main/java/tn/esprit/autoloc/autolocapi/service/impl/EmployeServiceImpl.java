
package tn.esprit.autoloc.autolocapi.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.autolocapi.domain.Employe;
import tn.esprit.autoloc.autolocapi.dto.EmployeDTO;
import tn.esprit.autoloc.autolocapi.mapper.EmployeMapper;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IEmployeRepository;
import tn.esprit.autoloc.autolocapi.service.IEmployeService;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    private final IEmployeRepository employeRepository;
    private final EmployeMapper employeMapper;

    @Override
    public EmployeDTO create(EmployeDTO dto) {
        Employe employe = employeMapper.toEntity(dto);

        if (employe.getIdEmploye() != null) {
            throw new IllegalArgumentException(
                    "Un nouvel employé ne doit pas avoir d'identifiant");
        }

        validerEmploye(employe);

        return employeMapper.toDTO(employeRepository.save(employe));
    }

    @Override
    public EmployeDTO findById(Long id) {
        return employeMapper.toDTO(findEntityById(id));
    }

    private Employe findEntityById(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employe", id));
    }

    @Override
    public List<EmployeDTO> findAll() {
        return employeRepository.findAll().stream()
                .map(employeMapper::toDTO)
                .toList();
    }

    @Override
    public EmployeDTO update(Long id, EmployeDTO dto) {
        Employe employe = employeMapper.toEntity(dto);

        Employe existant = findEntityById(id);

        validerEmploye(employe);

        existant.setNom(employe.getNom());
        existant.setPrenom(employe.getPrenom());
        existant.setRole(employe.getRole());

        return employeMapper.toDTO(employeRepository.save(existant));
    }

    @Override
    public void deleteById(Long id) {

        if (!employeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employe", id);
        }

        employeRepository.deleteById(id);
    }

    private void validerEmploye(Employe employe) {

        if (employe.getNom() == null ||
                employe.getNom().isBlank()) {
            throw new IllegalArgumentException(
                    "Le nom de l'employé est obligatoire");
        }

        if (employe.getPrenom() == null ||
                employe.getPrenom().isBlank()) {
            throw new IllegalArgumentException(
                    "Le prénom de l'employé est obligatoire");
        }

        if (employe.getRole() == null) {
            throw new IllegalArgumentException(
                    "Le rôle de l'employé est obligatoire");
        }
    }
}
