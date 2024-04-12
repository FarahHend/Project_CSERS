package Hend.BackendSpringboot.controller;

import Hend.BackendSpringboot.DTOs.PlanUrgenceDTO;
import Hend.BackendSpringboot.service.PlanUrgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class PlanUrgenceController {

    private final PlanUrgenceService planUrgenceService;

    @Autowired
    public PlanUrgenceController(PlanUrgenceService planUrgenceService) {
        this.planUrgenceService = planUrgenceService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<PlanUrgenceDTO>> getAllPlans() {
        List<PlanUrgenceDTO> plans = planUrgenceService.getAllPlans();
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PlanUrgenceDTO> getPlanById(@PathVariable Long id) {
        PlanUrgenceDTO plan = planUrgenceService.getPlanById(id);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PlanUrgenceDTO> createPlan(@RequestBody PlanUrgenceDTO planDTO) {
        PlanUrgenceDTO createdPlan = planUrgenceService.createPlan(planDTO);
        return new ResponseEntity<>(createdPlan, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PlanUrgenceDTO> updatePlan(@PathVariable Long id, @RequestBody PlanUrgenceDTO planDTO) {
        PlanUrgenceDTO updatedPlan = planUrgenceService.updatePlan(id, planDTO);
        return new ResponseEntity<>(updatedPlan, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        planUrgenceService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }
}

