package tn.esprit.autoloc.autolocapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tn.esprit.autoloc.autolocapi.domain.CategorieVehicule;
import tn.esprit.autoloc.autolocapi.domain.StatutVehicule;
import tn.esprit.autoloc.autolocapi.domain.Vehicule;
import tn.esprit.autoloc.autolocapi.repository.IVehiculeRepository;

import java.math.BigDecimal;

@SpringBootApplication
public class AutolocApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutolocApiApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(IVehiculeRepository vehiculeRepository) {
        return args -> {

            if (vehiculeRepository.count() == 0) {

                Vehicule v1 = new Vehicule();
                v1.setImmatriculation("123-TUN-4567");
                v1.setMarque("Peugeot");
                v1.setModele("208");
                v1.setCategorie(CategorieVehicule.CITADINE);
                v1.setTarifJournalier(new BigDecimal("120.00"));
                v1.setStatut(StatutVehicule.DISPONIBLE);

                Vehicule v2 = new Vehicule();
                v2.setImmatriculation("456-TUN-7890");
                v2.setMarque("Toyota");
                v2.setModele("Corolla");
                v2.setCategorie(CategorieVehicule.BERLINE);
                v2.setTarifJournalier(new BigDecimal("180.00"));
                v2.setStatut(StatutVehicule.DISPONIBLE);

                vehiculeRepository.save(v1);
                vehiculeRepository.save(v2);
            }
        };
    }
}
