
package tn.esprit.autoloc.autolocapi.service.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.autolocapi.domain.Paiement;
import tn.esprit.autoloc.autolocapi.dto.PaiementDTO;
import tn.esprit.autoloc.autolocapi.mapper.PaiementMapper;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IPaiementRepository;
import tn.esprit.autoloc.autolocapi.service.IPaiementService;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;
    private final PaiementMapper paiementMapper;

    @Override
    public PaiementDTO findById(Long id) {
        return paiementMapper.toDTO(findEntityById(id));
    }

    private Paiement findEntityById(Long id) {

        return paiementRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Paiement", id));
    }

    @Override
    public List<PaiementDTO> findAll() {

        return paiementRepository.findAll().stream()
                .map(paiementMapper::toDTO)
                .toList();
    }
}
