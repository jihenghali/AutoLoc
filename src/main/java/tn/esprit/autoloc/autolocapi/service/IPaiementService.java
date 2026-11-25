
package tn.esprit.autoloc.autolocapi.service;

import java.util.List;
import tn.esprit.autoloc.autolocapi.dto.PaiementDTO;

public interface IPaiementService {

    PaiementDTO findById(Long id);

    List<PaiementDTO> findAll();
}
