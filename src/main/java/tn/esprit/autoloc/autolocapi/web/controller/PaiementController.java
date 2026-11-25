
package tn.esprit.autoloc.autolocapi.web.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc.autolocapi.dto.PaiementDTO;
import tn.esprit.autoloc.autolocapi.service.IPaiementService;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final IPaiementService paiementService;

    @GetMapping("/{id}")
    public PaiementDTO findById(@PathVariable Long id) {
        return paiementService.findById(id);
    }

    @GetMapping
    public List<PaiementDTO> findAll() {
        return paiementService.findAll();
    }
}
