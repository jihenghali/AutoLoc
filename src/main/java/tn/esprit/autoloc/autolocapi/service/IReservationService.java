
package tn.esprit.autoloc.autolocapi.service;

import java.util.List;
import tn.esprit.autoloc.autolocapi.dto.ReservationDTO;

public interface IReservationService {

    ReservationDTO create(ReservationDTO reservation);

    ReservationDTO findById(Long id);

    List<ReservationDTO> findAll();

    ReservationDTO update(Long id, ReservationDTO reservation);

    void deleteById(Long id);
}
