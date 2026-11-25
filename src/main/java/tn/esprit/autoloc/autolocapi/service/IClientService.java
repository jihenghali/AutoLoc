
package tn.esprit.autoloc.autolocapi.service;

import java.util.List;
import tn.esprit.autoloc.autolocapi.dto.ClientDTO;

public interface IClientService {

    ClientDTO create(ClientDTO client);

    ClientDTO findById(Long id);

    List<ClientDTO> findAll();

    ClientDTO update(Long id, ClientDTO client);

    void deleteById(Long id);
}
