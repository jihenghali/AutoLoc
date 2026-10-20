
package tn.esprit.autoloc.autolocapi.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc.autolocapi.domain.Maintenance;
import tn.esprit.autoloc.autolocapi.service.IMaintenanceService;

@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;

    @PostMapping
    public Maintenance create(
            @RequestBody Maintenance maintenance) {
        return maintenanceService.create(maintenance);
    }

    @GetMapping("/{id}")
    public Maintenance findById(@PathVariable Long id) {
        return maintenanceService.findById(id);
    }

    @GetMapping
    public List<Maintenance> findAll() {
        return maintenanceService.findAll();
    }

    @PutMapping("/{id}")
    public Maintenance update(
            @PathVariable Long id,
            @RequestBody Maintenance maintenance) {
        return maintenanceService.update(id, maintenance);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        maintenanceService.deleteById(id);
    }
}
