package Hend.BackendSpringboot.service;

import Hend.BackendSpringboot.DTOs.RessourceDTO;
import Hend.BackendSpringboot.entity.EquipeIntervention;
import Hend.BackendSpringboot.entity.Ressource;
import Hend.BackendSpringboot.repository.EquipeInterventionRepository;
import Hend.BackendSpringboot.repository.RessourceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RessourceService {

    private final RessourceRepository ressourceRepository;
    private final EquipeInterventionRepository equipeInterventionRepository;

    @Autowired
    public RessourceService(RessourceRepository ressourceRepository, EquipeInterventionRepository equipeInterventionRepository) {
        this.ressourceRepository = ressourceRepository;
        this.equipeInterventionRepository = equipeInterventionRepository;
    }

    public RessourceDTO createRessource(RessourceDTO ressourceDTO) {
        Ressource ressource = new Ressource();
        // Map DTO fields to entity
        ressource.setNomRessource(ressourceDTO.getNomRessource());
        ressource.setTypeRessource(ressourceDTO.getTypeRessource());
        ressource.setLocalisation(ressourceDTO.getLocalisation());
        ressource.setTotalQuantite(ressourceDTO.getTotalQuantite());

        Ressource savedRessource = ressourceRepository.save(ressource);

        
        RessourceDTO savedDTO = new RessourceDTO();
        savedDTO.setNomRessource(savedRessource.getNomRessource());
        savedDTO.setTypeRessource(savedRessource.getTypeRessource());
        savedDTO.setLocalisation(savedRessource.getLocalisation());
        savedDTO.setTotalQuantite(savedRessource.getTotalQuantite());

        return savedDTO;
    }

    public List<RessourceDTO> getResourcesByEquipeName(String equipeName) {
        List<Ressource> ressources = ressourceRepository.findByReservationsEquipeInterventionNomEquipe(equipeName);
        System.out.println("Resources from repository: " + ressources); // Log the resources obtained from the repository
        List<RessourceDTO> dtos = ressources.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        System.out.println("DTOs after mapping: " + dtos); // Log the DTOs after mapping
        return dtos;
    }


    private RessourceDTO convertToDTO(Ressource ressource) {
        RessourceDTO dto = new RessourceDTO();
        //dto.setIdRessource(ressource.getIdRessource());
        dto.setNomRessource(ressource.getNomRessource());
        dto.setTypeRessource(ressource.getTypeRessource());
        dto.setLocalisation(ressource.getLocalisation());
        return dto;
    }
}
