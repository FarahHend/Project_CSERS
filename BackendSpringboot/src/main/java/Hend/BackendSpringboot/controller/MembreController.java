package Hend.BackendSpringboot.controller;

import Hend.BackendSpringboot.DTOs.MembreDTO;
import Hend.BackendSpringboot.entity.Membre;
import Hend.BackendSpringboot.service.MembreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/membres")
@CrossOrigin(origins = "http://localhost:4200")
public class MembreController {

    private static final String UPLOAD_DIR = "C:/Users/hend8/Desktop/BackendSpringboot/src/main/resources/static/Files/";


    private final MembreService membreService;

    private final ResourceLoader resourceLoader;

    public MembreController(MembreService membreService, ResourceLoader resourceLoader) {
        this.membreService = membreService;
        this.resourceLoader = resourceLoader;
    }

    @Autowired
    public MembreController(MembreService membreService) {
        this.membreService = membreService;
        resourceLoader = null;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<MembreDTO>> getAllMembres() {
        List<MembreDTO> membres = membreService.getAllMembres();
        return ResponseEntity.ok(membres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembreDTO> getMembreById(@PathVariable Long id) {
        MembreDTO membre = membreService.getMembreById(id);
        return ResponseEntity.ok(membre);
    }

    @GetMapping("/byEquipeId/{equipeId}")
    public ResponseEntity<List<MembreDTO>> getMembersByEquipeId(@PathVariable Long equipeId) {
        List<MembreDTO> membres = membreService.getMembersByEquipeId(equipeId);
        return ResponseEntity.ok(membres);
    }




    @PostMapping("/create")
    public ResponseEntity<MembreDTO> createMembre(@RequestBody MembreDTO membreDTO) {
        MembreDTO createdMembre = membreService.createMembre(membreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMembre);
    }

    @PostMapping("/createmembre")
    public ResponseEntity<MembreDTO> createMembre(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("email") String email,
            @RequestParam("poste") String poste,
            @RequestParam("number") String number,
            @RequestParam("competencesTechniques") String competencesTechniques,
            @RequestParam("certifications") String certifications,
            @RequestParam("experience") int experience,
            @RequestParam("equipeInterventionId") Long equipeInterventionId,
            @RequestParam("imageFile") MultipartFile imageFile
    ) {
        try {
            String originalFileName = imageFile.getOriginalFilename();
            String newFileName = originalFileName + "_"  + System.currentTimeMillis();
            MembreDTO membreDTO = new MembreDTO(null, nom, prenom, null, email, poste, number,
                    competencesTechniques, certifications, experience, equipeInterventionId);


            // Call service method to create member with image
            MembreDTO createdMembre = membreService.createMembreWithImage(membreDTO, imageFile, newFileName);

            // Remove the imageFile field from the response
            createdMembre.setImageFile(null);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdMembre);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<MembreDTO>> searchByName(@RequestParam String name) {
        List<MembreDTO> membres = membreService.searchByName(name);
        return ResponseEntity.ok(membres);
    }

    @GetMapping("/searchByPoste")
    public ResponseEntity<List<MembreDTO>> searchByPoste(@RequestParam String poste) {
        List<MembreDTO> membres = membreService.searchByPoste(poste);
        return ResponseEntity.ok(membres);
    }

    @GetMapping("/searchByEmail")
    public ResponseEntity<List<MembreDTO>> searchByEmail(@RequestParam("email") String email) {
        List<MembreDTO> membres = membreService.searchByEmail(email);
        return ResponseEntity.ok(membres);
    }




    @PutMapping("/update/{id}")
    public ResponseEntity<MembreDTO> updateMembre(@PathVariable Long id, @RequestBody MembreDTO membreDTO) {
        MembreDTO updatedMembre = membreService.updateMembre(id, membreDTO);
        return ResponseEntity.ok(updatedMembre);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMembre(@PathVariable Long id) {
        membreService.deleteMembre(id);
        return ResponseEntity.ok("L'équipe avec l'ID " + id + " a été supprimée avec succès.");
    }
}

