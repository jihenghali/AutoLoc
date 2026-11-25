
package tn.esprit.autoloc.autolocapi.service;

import java.util.List;
import tn.esprit.autoloc.autolocapi.dto.MaintenanceDTO;

public interface IMaintenanceService {

    MaintenanceDTO create(MaintenanceDTO maintenance);

    MaintenanceDTO findById(Long id);

    List<MaintenanceDTO> findAll();

    MaintenanceDTO update(Long id, MaintenanceDTO maintenance);

    void deleteById(Long id);
}
