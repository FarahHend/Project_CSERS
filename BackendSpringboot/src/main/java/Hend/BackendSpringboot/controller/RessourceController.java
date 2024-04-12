package Hend.BackendSpringboot.controller;

import Hend.BackendSpringboot.DTOs.RessourceDTO;
import Hend.BackendSpringboot.service.RessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ressources")
public class RessourceController {

    private final RessourceService ressourceService;


    @Autowired
    public RessourceController(RessourceService ressourceService) {
        this.ressourceService = ressourceService;
    }

    @PostMapping("/create")
    public ResponseEntity<RessourceDTO> createRessource(@RequestBody RessourceDTO ressourceDTO) {
        RessourceDTO createdRessource = ressourceService.createRessource(ressourceDTO);
        return new ResponseEntity<>(createdRessource, HttpStatus.CREATED);
    }
    @GetMapping("/equipe/{equipeName}")
    public ResponseEntity<RessourceDTO> getResourcesByEquipeName(@PathVariable String equipeName) {
        List<RessourceDTO> dtos = ressourceService.getResourcesByEquipeName(equipeName);
        if (dtos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dtos.get(0)); // Return the first element of the list
    }
}

