package Hend.BackendSpringboot.service;

import Hend.BackendSpringboot.DTOs.EquipeInterventionDTO;
import Hend.BackendSpringboot.entity.EquipeIntervention;
import Hend.BackendSpringboot.entity.Membre;
import Hend.BackendSpringboot.repository.EquipeInterventionRepository;
import Hend.BackendSpringboot.repository.MembreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipeInterventionService {

    private final EquipeInterventionRepository equipeInterventionRepository;

    @Autowired
    private MembreRepository membreRepository;

    @Autowired
    public EquipeInterventionService(EquipeInterventionRepository equipeInterventionRepository) {
        this.equipeInterventionRepository = equipeInterventionRepository;
    }

    public List<EquipeInterventionDTO> getAllEquipes() {
        List<EquipeIntervention> equipes = equipeInterventionRepository.findAll();
        return equipes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EquipeInterventionDTO getEquipeById(Long id) {
        Optional<EquipeIntervention> optionalEquipe = equipeInterventionRepository.findById(id);
        if (optionalEquipe.isPresent()) {
            return convertToDTO(optionalEquipe.get());
        }
        throw new RuntimeException("Équipe non trouvée avec l'ID: " + id);
    }

    public List<byte[]> getMemberImagesByEquipeId(Long equipeId) {
        List<Membre> membres = membreRepository.findByEquipeIntervention_IdEquipe(equipeId);
        if (membres.isEmpty()) {
            throw new RuntimeException("Aucun membre trouvé pour l'équipe avec l'ID: " + equipeId);
        }
        return membres.stream()
                .map(Membre::getImageFile) // Assuming the image data is stored as byte[]
                .collect(Collectors.toList());
    }

    public EquipeInterventionDTO createEquipe(EquipeInterventionDTO equipeInterventionDTO) {
        EquipeIntervention equipe = convertToEntity(equipeInterventionDTO);
        EquipeIntervention savedEquipe = equipeInterventionRepository.save(equipe);
        return convertToDTO(savedEquipe);
    }

    public EquipeInterventionDTO updateEquipe(Long id, EquipeInterventionDTO equipeInterventionDTO) {
        Optional<EquipeIntervention> optionalEquipe = equipeInterventionRepository.findById(id);
        if (optionalEquipe.isPresent()) {
            EquipeIntervention existingEquipe = optionalEquipe.get();
            BeanUtils.copyProperties(equipeInterventionDTO, existingEquipe, "idEquipe");
            EquipeIntervention updatedEquipe = equipeInterventionRepository.save(existingEquipe);
            return convertToDTO(updatedEquipe);
        }
        throw new RuntimeException("Équipe non trouvée avec l'ID: " + id);
    }

    public void deleteEquipe(Long id) {
        if (equipeInterventionRepository.existsById(id)) {
            equipeInterventionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Équipe non trouvée avec l'ID: " + id);
        }
    }

    private EquipeInterventionDTO convertToDTO(EquipeIntervention equipe) {
        EquipeInterventionDTO dto = new EquipeInterventionDTO();
        dto.setIdEquipe(equipe.getIdEquipe());
        dto.setNomEquipe(equipe.getNomEquipe());
        dto.setDescription(equipe.getDescription());
        dto.setDisponibilite(equipe.getDisponibilite());
        return dto;
    }


    private EquipeIntervention convertToEntity(EquipeInterventionDTO dto) {
        EquipeIntervention equipe = new EquipeIntervention();
        BeanUtils.copyProperties(dto, equipe);
        return equipe;
    }
}

