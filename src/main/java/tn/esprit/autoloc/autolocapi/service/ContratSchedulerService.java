package tn.esprit.autoloc.autolocapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domain.Contrat;
import tn.esprit.autoloc.autolocapi.repository.IContratRepository;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContratSchedulerService {

    private final IContratRepository contratRepository;

    @Value("${autoloc.scheduler.zone:Africa/Tunis}")
    private String zone;

    @Scheduled(cron = "0 0 7 * * *", zone = "${autoloc.scheduler.zone:Africa/Tunis}")
    public void identifierContratsArrivantAEcheance() {
        LocalDate aujourdhui = LocalDate.now(ZoneId.of(zone));
        for (Contrat contrat : contratRepository.findByValideTrueAndReservationDateFinBetween(
                aujourdhui, aujourdhui.plusDays(1))) {
            log.info("Contrat {} arrivant à échéance le {}",
                    contrat.getIdContrat(), contrat.getReservation().getDateFin());
        }
    }
}
