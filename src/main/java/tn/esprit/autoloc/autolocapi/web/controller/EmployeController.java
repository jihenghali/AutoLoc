
package tn.esprit.autoloc.autolocapi.web.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import tn.esprit.autoloc.autolocapi.dto.EmployeDTO;
import tn.esprit.autoloc.autolocapi.service.IEmployeService;

@RestController
@RequestMapping("/api/employes")
@RequiredArgsConstructor
public class EmployeController {

    private final IEmployeService employeService;

    @PostMapping
    public EmployeDTO create(@Valid @RequestBody EmployeDTO employe) {
        return employeService.create(employe);
    }

    @GetMapping("/{id}")
    public EmployeDTO findById(@PathVariable Long id) {
        return employeService.findById(id);
    }

    @GetMapping
    public List<EmployeDTO> findAll() {
        return employeService.findAll();
    }

    @PutMapping("/{id}")
    public EmployeDTO update(@PathVariable Long id,
                          @Valid @RequestBody EmployeDTO employe) {
        return employeService.update(id, employe);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        employeService.deleteById(id);
    }
}
