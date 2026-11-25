
package tn.esprit.autoloc.autolocapi.service;

import java.util.List;
import tn.esprit.autoloc.autolocapi.dto.EmployeDTO;

public interface IEmployeService {

    EmployeDTO create(EmployeDTO employe);

    EmployeDTO findById(Long id);

    List<EmployeDTO> findAll();

    EmployeDTO update(Long id, EmployeDTO employe);

    void deleteById(Long id);
}
