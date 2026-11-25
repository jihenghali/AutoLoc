
package tn.esprit.autoloc.autolocapi.service;

import java.util.List;
import tn.esprit.autoloc.autolocapi.dto.EquipementDTO;

public interface IEquipementService {

    EquipementDTO create(EquipementDTO equipement);

    EquipementDTO findById(Long id);

    List<EquipementDTO> findAll();

    EquipementDTO update(Long id, EquipementDTO equipement);

    void deleteById(Long id);
}
