package Hend.BackendSpringboot.controller;

import Hend.BackendSpringboot.DTOs.EquipeInterventionDTO;
import Hend.BackendSpringboot.service.EquipeInterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipes")
public class EquipeInterventionController {

    private final EquipeInterventionService equipeInterventionService;

    @Autowired
    public EquipeInterventionController(EquipeInterventionService equipeInterventionService) {
        this.equipeInterventionService = equipeInterventionService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<EquipeInterventionDTO>> getAllEquipes() {
        List<EquipeInterventionDTO> equipes = equipeInterventionService.getAllEquipes();
        return ResponseEntity.ok(equipes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipeInterventionDTO> getEquipeById(@PathVariable Long id) {
        EquipeInterventionDTO equipe = equipeInterventionService.getEquipeById(id);
        return ResponseEntity.ok(equipe);
    }

    @GetMapping("/{equipeId}/membres/images")
    public List<byte[]> getMemberImagesByEquipeId(@PathVariable Long equipeId) {
        return equipeInterventionService.getMemberImagesByEquipeId(equipeId);
    }

    @PostMapping("/create")
    public ResponseEntity<EquipeInterventionDTO> createEquipe(@RequestBody EquipeInterventionDTO equipeInterventionDTO) {
        EquipeInterventionDTO createdEquipe = equipeInterventionService.createEquipe(equipeInterventionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEquipe);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EquipeInterventionDTO> updateEquipe(@PathVariable Long id, @RequestBody EquipeInterventionDTO equipeInterventionDTO) {
        EquipeInterventionDTO updatedEquipe = equipeInterventionService.updateEquipe(id, equipeInterventionDTO);
        return ResponseEntity.ok(updatedEquipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEquipe(@PathVariable Long id) {
        equipeInterventionService.deleteEquipe(id);
        return ResponseEntity.ok("L'équipe avec l'ID " + id + " a été supprimée avec succès.");
    }

}

