
package tn.esprit.autoloc.autolocapi.web.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc.autolocapi.dto.MaintenanceDTO;
import tn.esprit.autoloc.autolocapi.service.IMaintenanceService;

@RestController
@RequestMapping("/api/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;

    @PostMapping
    public MaintenanceDTO create(
            @Valid @RequestBody MaintenanceDTO maintenance) {
        return maintenanceService.create(maintenance);
    }

    @GetMapping("/{id}")
    public MaintenanceDTO findById(@PathVariable Long id) {
        return maintenanceService.findById(id);
    }

    @GetMapping
    public List<MaintenanceDTO> findAll() {
        return maintenanceService.findAll();
    }

    @PutMapping("/{id}")
    public MaintenanceDTO update(
            @PathVariable Long id,
            @Valid @RequestBody MaintenanceDTO maintenance) {
        return maintenanceService.update(id, maintenance);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        maintenanceService.deleteById(id);
    }
}
