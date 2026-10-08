# Atelier 3 - Notes Repository

## Choix d'interface

| Interface | Etend | Justification |
| --- | --- | --- |
| IAgenceRepository | JpaRepository<Agence, Long> | CRUD complet, findAll renvoie une List, tri et pagination disponibles. |
| IEmployeRepository | JpaRepository<Employe, Long> | CRUD complet, findAll renvoie une List, tri et pagination disponibles. |
| IVehiculeRepository | JpaRepository<Vehicule, Long> | CRUD complet, findAll renvoie une List, saveAndFlush disponible. |
| IEquipementRepository | JpaRepository<Equipement, Long> | CRUD complet, findAll renvoie une List, tri et pagination disponibles. |
| IClientRepository | JpaRepository<Client, Long> | CRUD complet, findAll renvoie une List, tri et pagination disponibles. |
| IReservationRepository | JpaRepository<Reservation, Long> | CRUD complet, findAll renvoie une List, tri et pagination disponibles. |
| IContratRepository | JpaRepository<Contrat, Long> | CRUD complet, findAll renvoie une List, saveAndFlush disponible. |
| IPaiementRepository | JpaRepository<Paiement, Long> | Lecture des paiements possible ; la creation et la suppression restent pilotees par Contrat. |
| IMaintenanceRepository | JpaRepository<Maintenance, Long> | CRUD complet, findAll renvoie une List, tri et pagination disponibles. |

## Anomalies relevees et corrections

| Anomalie | Regle / explication | Correction apportee |
| --- | --- | --- |
| Repository `VehiculeRepository` sans prefixe `I`. | Convention AutoLoc de l'atelier : chaque interface repository commence par `I`. | Remplacement par `IVehiculeRepository` et mise a jour de l'injection dans `AutolocApiApplication`. |
| Imports generiques dans `Agence`. | Les imports explicites rendent les dependances JPA utilisees par l'entite plus lisibles. | Remplacement de `jakarta.persistence.*` par les imports JPA utilises. |
| Imports generiques dans `Contrat`. | Les imports explicites rendent les annotations de cascade et d'association plus lisibles. | Remplacement de `jakarta.persistence.*` par les imports JPA utilises. |
| Imports generiques dans `Vehicule`. | Les imports explicites evitent un import global dans une entite centrale du modele. | Remplacement de `jakarta.persistence.*` par les imports JPA utilises. |

## Verification

Commande executee :

```bash
./mvnw.cmd spring-boot:run "-Dspring-boot.run.arguments=--spring.main.web-application-type=none"
```

Resultat observe :

```text
Found 9 JPA repository interfaces.
```

Remarque : l'analyse SonarQube for IDE doit etre lancee dans IntelliJ IDEA comme indique dans l'atelier.
