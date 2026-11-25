
package tn.esprit.autoloc.autolocapi.service;

import java.util.List;
import tn.esprit.autoloc.autolocapi.dto.ContratDTO;

public interface IContratService {

    ContratDTO create(ContratDTO contrat);

    ContratDTO findById(Long id);

    List<ContratDTO> findAll();

    ContratDTO update(Long id, ContratDTO contrat);

    void deleteById(Long id);
}
