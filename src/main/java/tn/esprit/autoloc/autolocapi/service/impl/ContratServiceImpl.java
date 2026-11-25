
package tn.esprit.autoloc.autolocapi.service.impl;

import java.math.BigDecimal;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.dto.ContratDTO;
import tn.esprit.autoloc.autolocapi.mapper.ContratMapper;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IContratRepository;
import tn.esprit.autoloc.autolocapi.service.IContratService;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;
    private final ContratMapper contratMapper;

    @Override
    public ContratDTO create(ContratDTO dto) {
        Contrat contrat = contratMapper.toEntity(dto);

        if (contrat.getIdContrat() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau contrat ne doit pas avoir d'identifiant");
        }

        validerContrat(contrat);

        return contratMapper.toDTO(contratRepository.save(contrat));
    }

    @Override
    public ContratDTO findById(Long id) {
        return contratMapper.toDTO(findEntityById(id));
    }

    private Contrat findEntityById(Long id) {

        return contratRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contrat", id));
    }

    @Override
    public List<ContratDTO> findAll() {

        return contratRepository.findAll().stream()
                .map(contratMapper::toDTO)
                .toList();
    }

    @Override
    public ContratDTO update(Long id, ContratDTO dto) {
        Contrat contrat = contratMapper.toEntity(dto);

        Contrat existant = findEntityById(id);

        validerContrat(contrat);

        existant.setDateSignature(contrat.getDateSignature());
        existant.setMontantTotal(contrat.getMontantTotal());
        existant.setValide(contrat.getValide());

        return contratMapper.toDTO(contratRepository.save(existant));
    }

    @Override
    public void deleteById(Long id) {

        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat", id);
        }

        contratRepository.deleteById(id);
    }

    private void validerContrat(Contrat contrat) {

        if (contrat.getMontantTotal() == null ||
                contrat.getMontantTotal().compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Le montant total doit être positif ou nul");
        }
    }
}
