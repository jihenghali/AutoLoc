
package tn.esprit.autoloc.autolocapi.service.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.autolocapi.domain.Paiement;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IPaiementRepository;
import tn.esprit.autoloc.autolocapi.service.IPaiementService;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;

    @Override
    public Paiement findById(Long id) {

        return paiementRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Paiement", id));
    }

    @Override
    public List<Paiement> findAll() {

        return paiementRepository.findAll();
    }
}
