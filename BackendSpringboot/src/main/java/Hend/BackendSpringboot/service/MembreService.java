package Hend.BackendSpringboot.service;

import Hend.BackendSpringboot.DTOs.MembreDTO;
import Hend.BackendSpringboot.entity.EquipeIntervention;
import Hend.BackendSpringboot.entity.Membre;
import Hend.BackendSpringboot.repository.EquipeInterventionRepository;
import Hend.BackendSpringboot.repository.MembreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StreamUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MembreService {

    private final MembreRepository membreRepository;
    private final EquipeInterventionRepository equipeInterventionRepository;

    @Autowired
    public MembreService(MembreRepository membreRepository, EquipeInterventionRepository equipeInterventionRepository) {
        this.membreRepository = membreRepository;
        this.equipeInterventionRepository = equipeInterventionRepository;
    }

    public List<MembreDTO> getAllMembres() {
        List<Membre> membres = membreRepository.findAll();
        return membres.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public MembreDTO getMembreById(Long id) {
        Optional<Membre> optionalMembre = membreRepository.findById(id);
        if (optionalMembre.isPresent()) {
            return convertToDTO(optionalMembre.get());
        }
        throw new RuntimeException("Membre not found with ID: " + id);
    }

    public List<MembreDTO> getMembersByEquipeId(Long equipeId) {
        List<Membre> membres = membreRepository.findByEquipeIntervention_IdEquipe(equipeId);
        return membres.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public MembreDTO createMembre(MembreDTO membreDTO) {
        Membre membre = convertToEntity(membreDTO);
        Membre savedMembre = membreRepository.save(membre);
        return convertToDTO(savedMembre);
    }

    public MembreDTO createMembreWithImage(MembreDTO membreDTO, MultipartFile imageFile, String newFileName) throws IOException {
        try {
            Membre membre = convertToEntity(membreDTO);

            if (imageFile != null && !imageFile.isEmpty()) {
                byte[] imageBytes = imageFile.getBytes();
                membre.setImageFile(imageBytes);
            }

            membreDTO.setImageName(newFileName);

            Membre savedMembre = membreRepository.save(membre);

            membreDTO.setIdMembre(savedMembre.getIdMembre());
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return membreDTO;
    }



    public MembreDTO updateMembre(Long id, MembreDTO membreDTO) {
        Optional<Membre> optionalMembre = membreRepository.findById(id);
        if (optionalMembre.isPresent()) {
            Membre existingMembre = optionalMembre.get();

            // Update the equipeInterventionId if provided
            if (membreDTO.getEquipeInterventionId() != null) {
                EquipeIntervention equipeIntervention = equipeInterventionRepository.findById(membreDTO.getEquipeInterventionId())
                        .orElseThrow(() -> new IllegalArgumentException("EquipeIntervention with ID " + membreDTO.getEquipeInterventionId() + " not found"));
                existingMembre.setEquipeIntervention(equipeIntervention);
            }

            // Save the updated Membre
            Membre updatedMembre = membreRepository.save(existingMembre);
            return convertToDTO(updatedMembre);
        }
        throw new RuntimeException("Membre not found with ID: " + id);
    }



    public void deleteMembre(Long id) {
        if (membreRepository.existsById(id)) {
            membreRepository.deleteById(id);
        } else {
            throw new RuntimeException("Membre not found with ID: " + id);
        }
    }

    public List<MembreDTO> searchByName(String name) {
        List<Membre> membres = membreRepository.findByNomContainingIgnoreCase(name);
        return membres.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MembreDTO> searchByPoste(String poste) {
        List<Membre> membres = membreRepository.findByPosteContainingIgnoreCase(poste);
        return membres.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MembreDTO> searchByEmail(String email) {
        List<Membre> membres = membreRepository.findByEmailContainingIgnoreCase(email);
        return membres.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    private MembreDTO convertToDTO(Membre membre) {
        MembreDTO dto = new MembreDTO();
        BeanUtils.copyProperties(membre, dto);
        if (membre.getEquipeIntervention() != null) {
            dto.setEquipeInterventionId(membre.getEquipeIntervention().getIdEquipe());
        }
        return dto;
    }

    private Membre convertToEntity(MembreDTO dto) {
        Membre membre = new Membre();
        BeanUtils.copyProperties(dto, membre);

        // Convert MultipartFile to byte array
        if (dto.getImageFile() != null && dto.getImageFile().length > 0) {
            byte[] imageBytes = dto.getImageFile();
            membre.setImageFile(imageBytes);
        }

        if (dto.getEquipeInterventionId() != null) {
            EquipeIntervention equipeIntervention = equipeInterventionRepository.findById(dto.getEquipeInterventionId())
                    .orElseThrow(() -> new IllegalArgumentException("EquipeIntervention with ID " + dto.getEquipeInterventionId() + " not found"));
            membre.setEquipeIntervention(equipeIntervention);
        } else {
            throw new IllegalArgumentException("EquipeInterventionId is required");
        }

        return membre;
    }
}
