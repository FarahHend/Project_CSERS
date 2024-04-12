package Hend.BackendSpringboot.DTOs;

import java.util.List;

public class PlanUrgenceDTO {

    private Long idPlan;
    private String nomPlan;
    private String description;
    private String actionsRequises;
    private List<Long> typeIncidentIds;

    // Constructors, getters, and setters

    public PlanUrgenceDTO() {
    }

    public PlanUrgenceDTO(String nomPlan, String description, String actionsRequises, List<Long> typeIncidentIds) {
        this.nomPlan = nomPlan;
        this.description = description;
        this.actionsRequises = actionsRequises;
        this.typeIncidentIds = typeIncidentIds;
    }

    public Long getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Long idPlan) {
        this.idPlan = idPlan;
    }

    public String getNomPlan() {
        return nomPlan;
    }

    public void setNomPlan(String nomPlan) {
        this.nomPlan = nomPlan;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActionsRequises() {
        return actionsRequises;
    }

    public void setActionsRequises(String actionsRequises) {
        this.actionsRequises = actionsRequises;
    }

    public List<Long> getTypeIncidentIds() {
        return typeIncidentIds;
    }

    public void setTypeIncidentIds(List<Long> typeIncidentIds) {
        this.typeIncidentIds = typeIncidentIds;
    }
}
