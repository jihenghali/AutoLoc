
# Atelier 4 — IoC, DI et couche Service

## A. Modes d'injection comparés

| Critère | Injection par constructeur | Injection par attribut |
|---|---|---|
| Champ final possible ? | Oui | Non |
| Dépendance visible dans le constructeur ? | Oui | Non |
| Utilisable avec new hors conteneur sans dépendance null ? | Oui, si le repository est fourni | Non, le repository reste null |
| Signalé par SonarQube ? | Non | Oui, règle S6813 |

Conclusion : L'injection par constructeur est préférable car elle rend les dépendances explicites, permet d'utiliser des champs final et facilite les tests.

## B. Services

| Service | Dépendances injectées | Mode | Justification |
|---|---|---|---|
| AgenceServiceImpl | IAgenceRepository | Constructeur | Accéder aux données des agences et appliquer les règles métier |
| EmployeServiceImpl | IEmployeRepository | Constructeur | Gérer les employés et vérifier les attributs obligatoires |
| VehiculeServiceImpl | IVehiculeRepository | Constructeur | Gérer les véhicules et vérifier les valeurs numériques |
| EquipementServiceImpl | IEquipementRepository | Constructeur | Gérer les équipements et vérifier le libellé et la quantité |
| ClientServiceImpl | IClientRepository | Constructeur | Gérer les clients et vérifier les informations obligatoires |
| ReservationServiceImpl | IReservationRepository | Constructeur | Gérer les réservations et vérifier la cohérence des dates |
| ContratServiceImpl | IContratRepository | Constructeur | Gérer les contrats et vérifier le montant total |
| PaiementServiceImpl | IPaiementRepository | Constructeur | Consulter les paiements en lecture seule |
| MaintenanceServiceImpl | IMaintenanceRepository | Constructeur | Gérer les maintenances et vérifier les coûts et les dates |

Justification générale : Les services utilisent l'injection par constructeur avec @RequiredArgsConstructor et des attributs final. Spring fournit automatiquement les repositories nécessaires, sans utiliser new.

## C. Messages d'erreur du conteneur Spring

### Message 1 — Bean introuvable

Exception : NoSuchBeanDefinitionException.

Bean concerné : IContratService, nécessaire à ContratController.

Causes possibles :
- L'annotation @Service est absente de ContratServiceImpl.
- ContratServiceImpl est placé en dehors des packages analysés par Spring.

Correction :
Ajouter @Service sur ContratServiceImpl et vérifier que la classe se trouve dans un sous-package du package racine de l'application Spring Boot.

### Message 2 — Plusieurs beans trouvés

Exception : NoUniqueBeanDefinitionException.

Bean concerné : INotificateur, avec deux implémentations : EmailNotificateur et SmsNotificateur.

Cause :
Spring trouve deux beans du même type et ne sait pas lequel injecter.

Corrections possibles :
- Solution 1 : Ajouter @Primary sur la classe EmailNotificateur pour la sélectionner par défaut.
- Solution 2 : Ajouter @Qualifier("smsNotificateur") sur le paramètre du constructeur de NotificationController pour choisir explicitement SmsNotificateur.

### Message 3 — Dépendance circulaire

Exception : BeanCurrentlyInCreationException.

Beans concernés : ClientServiceImpl et ReservationServiceImpl.

Cause :
ClientServiceImpl dépend de ReservationServiceImpl, et ReservationServiceImpl dépend de ClientServiceImpl.

Avec l'injection par constructeur, chaque service doit recevoir ses dépendances dès sa création. Spring ne peut donc pas construire les deux services car chacun attend l'autre.

Correction :
Supprimer la dépendance circulaire en extrayant la logique commune dans un troisième service indépendant.


## D. Anomalies SonarQube for IDE

### 1. Analyse du code

L'analyse SonarQube permet de détecter les mauvaises pratiques et d'améliorer la qualité du code Java.

Les classes ContratServiceImpl et ResourceNotFoundException sont analysées afin d'identifier les anomalies.

### 2. Tableau des anomalies

| Règle | Anomalie détectée | Explication | Correction |
|---|---|---|---|
| java:S6813 | Injection par attribut | L'utilisation de @Autowired sur un attribut est déconseillée. | Utiliser @RequiredArgsConstructor et un attribut final pour l'injection par constructeur. |
| java:S125 | Code commenté | Des lignes de code Java sont conservées en commentaire. | Supprimer les lignes de code commentées inutiles. |
| java:S1128 | Import inutilisé | Un import est déclaré mais n'est plus utilisé. | Supprimer l'import inutile. |
| java:S112 | Exception générique | L'utilisation directe de RuntimeException est déconseillée. | Utiliser l'exception métier ResourceNotFoundException. |

### 3. Expérience avec RuntimeException

Dans la méthode findById de ContratServiceImpl, nous remplaçons temporairement ResourceNotFoundException par RuntimeException.

Cette modification permet de vérifier la règle java:S112, qui déconseille l'utilisation d'une exception générique.

La correction consiste à restaurer ResourceNotFoundException afin d'avoir une exception métier explicite.

### 4. Conclusion

L'analyse SonarQube permet d'améliorer la qualité et la maintenabilité du code.

Les bonnes pratiques retenues sont :
- Privilégier l'injection par constructeur.
- Supprimer le code commenté inutile.
- Supprimer les imports inutilisés.
- Utiliser des exceptions métier plutôt que des exceptions génériques.

## E. Questions de compréhension

### 1. Où est le new de ContratServiceImpl et qui l'exécute ?

Il n'y a pas de new explicite dans notre code.
Le conteneur Spring IoC crée automatiquement l'instance
de ContratServiceImpl grâce à l'annotation @Service.

### 2. Pourquoi un contrôleur dépend-il de IContratService
et non de ContratServiceImpl ?

Pour réduire le couplage entre les couches.
Le contrôleur dépend d'une interface, ce qui permet
de changer l'implémentation sans modifier le contrôleur.

### 3. Pourquoi un service singleton doit-il rester sans état ?

Un singleton est partagé entre plusieurs utilisateurs
et plusieurs requêtes.

S'il contient des données modifiables propres à une requête,
un utilisateur pourrait modifier les données d'un autre.

Exemple : stocker un contrat courant dans un attribut
du service pourrait provoquer des mélanges entre requêtes.

### 4. Pourquoi update charge-t-il l'entité existante
avant d'appeler save ?

Pour modifier uniquement les attributs nécessaires
sans écraser les autres données.

Cela permet notamment de conserver les associations
et les collections existantes, comme les paiements
d'un contrat.

### 5. Différences entre les annotations et Paiement

@Component :
Déclare automatiquement une classe comme bean Spring.

@Bean :
Déclare explicitement un bean à partir d'une méthode
de configuration.

@Primary :
Définit le bean choisi par défaut lorsqu'il existe
plusieurs implémentations du même type.

@Qualifier :
Permet de sélectionner explicitement un bean précis.

IPaiementService :
N'expose que findById et findAll car les paiements
sont gérés par le Contrat.
La création, la modification et la suppression
ne sont pas autorisées directement dans ce service.
