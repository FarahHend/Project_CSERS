package Hend.BackendSpringboot.service;

import Hend.BackendSpringboot.DTOs.PlanUrgenceDTO;
import Hend.BackendSpringboot.entity.PlanUrgence;
import Hend.BackendSpringboot.repository.PlanUrgenceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanUrgenceService {

    private final PlanUrgenceRepository planUrgenceRepository;

    @Autowired
    public PlanUrgenceService(PlanUrgenceRepository planUrgenceRepository) {
        this.planUrgenceRepository = planUrgenceRepository;
    }

    public List<PlanUrgenceDTO> getAllPlans() {
        List<PlanUrgence> plans = planUrgenceRepository.findAll();
        return plans.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PlanUrgenceDTO getPlanById(Long id) {
        PlanUrgence plan = planUrgenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PlanUrgence not found with ID: " + id));
        return convertToDTO(plan);
    }

    public PlanUrgenceDTO createPlan(PlanUrgenceDTO planDTO) {
        PlanUrgence plan = new PlanUrgence();
        BeanUtils.copyProperties(planDTO, plan);
        PlanUrgence savedPlan = planUrgenceRepository.save(plan);
        return convertToDTO(savedPlan);
    }

    public PlanUrgenceDTO updatePlan(Long id, PlanUrgenceDTO planDTO) {
        PlanUrgence existingPlan = planUrgenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PlanUrgence not found with ID: " + id));

        // Update existing plan with DTO data
        BeanUtils.copyProperties(planDTO, existingPlan);
        PlanUrgence updatedPlan = planUrgenceRepository.save(existingPlan);
        return convertToDTO(updatedPlan);
    }

    public void deletePlan(Long id) {
        PlanUrgence plan = planUrgenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PlanUrgence not found with ID: " + id));
        planUrgenceRepository.delete(plan);
    }

    private PlanUrgenceDTO convertToDTO(PlanUrgence plan) {
        PlanUrgenceDTO dto = new PlanUrgenceDTO();
        BeanUtils.copyProperties(plan, dto);
        return dto;
    }
}

