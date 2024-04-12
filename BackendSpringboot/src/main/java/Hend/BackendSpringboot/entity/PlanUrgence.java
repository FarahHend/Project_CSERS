package Hend.BackendSpringboot.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "plan_urgence")
public class PlanUrgence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan")
    private Long idPlan;

    @Column(name = "nom_plan")
    private String nomPlan;

    @Column(name = "description")
    private String description;

    @Column(name = "actions_requises")
    private String actionsRequises;

    @OneToMany(mappedBy = "planUrgence", cascade = CascadeType.ALL)
    private List<TypeIncident> typeIncidents;

    // Constructors, getters, and setters

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

    public List<TypeIncident> getTypeIncidents() {
        return typeIncidents;
    }

    public void setTypeIncidents(List<TypeIncident> typeIncidents) {
        this.typeIncidents = typeIncidents;
    }
}

