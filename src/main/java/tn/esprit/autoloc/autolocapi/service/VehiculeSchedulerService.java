package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.autolocapi.domain.Reservation;
import tn.esprit.autoloc.autolocapi.domain.StatutReservation;
import tn.esprit.autoloc.autolocapi.domain.StatutVehicule;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.repository.IReservationRepository;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class VehiculeSchedulerService {

    private final IReservationRepository reservationRepository;

    @Value("${autoloc.scheduler.zone:Africa/Tunis}")
    private String zone;

    @Scheduled(cron = "0 0 1 * * *", zone = "${autoloc.scheduler.zone:Africa/Tunis}")
    @Transactional
    public void terminerLocationsEchues() {
        LocalDate aujourdhui = LocalDate.now(ZoneId.of(zone));
        for (Reservation reservation : reservationRepository.findByStatutAndDateFinBefore(
                StatutReservation.CONFIRMEE, aujourdhui)) {
            reservation.setStatut(StatutReservation.TERMINEE);

            Vehicule vehicule = reservation.getVehicule();
            if (vehicule != null && vehicule.getStatut() == StatutVehicule.LOUE
                    && !reservationRepository
                            .existsByVehiculeAndStatutAndDateDebutLessThanEqualAndDateFinGreaterThanEqual(
                                    vehicule, StatutReservation.CONFIRMEE, aujourdhui, aujourdhui)) {
                vehicule.setStatut(StatutVehicule.DISPONIBLE);
            }
        }
    }
}
