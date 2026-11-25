
package tn.esprit.autoloc.autolocapi.service;

import java.util.List;
import tn.esprit.autoloc.autolocapi.dto.AgenceDTO;

public interface IAgenceService {

    AgenceDTO create(AgenceDTO agence);

    AgenceDTO findById(Long id);

    List<AgenceDTO> findAll();

    AgenceDTO update(Long id, AgenceDTO agence);

    void deleteById(Long id);
}
