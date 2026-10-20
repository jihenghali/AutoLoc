
package tn.esprit.autoloc.autolocapi.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc.autolocapi.domain.Agence;
import tn.esprit.autoloc.autolocapi.service.IAgenceService;

@RestController
@RequestMapping("/api/agences")
@RequiredArgsConstructor
public class AgenceController {

    private final IAgenceService agenceService;

    @PostMapping
    public Agence create(@RequestBody Agence agence) {
        return agenceService.create(agence);
    }

    @GetMapping("/{id}")
    public Agence findById(@PathVariable Long id) {
        return agenceService.findById(id);
    }

    @GetMapping
    public List<Agence> findAll() {
        return agenceService.findAll();
    }

    @PutMapping("/{id}")
    public Agence update(@PathVariable Long id,
                         @RequestBody Agence agence) {
        return agenceService.update(id, agence);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        agenceService.deleteById(id);
    }
}
