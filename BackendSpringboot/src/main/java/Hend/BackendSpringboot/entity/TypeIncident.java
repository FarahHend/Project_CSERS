package Hend.BackendSpringboot.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "type_incident")
public class TypeIncident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_typeincident")
    private Long idTypeIncident;

    @Column(name = "nom_typeincident")
    private String nomTypeIncident;

    @Column(name = "description")
    private String description;

    @Column(name = "niveau_risque")
    private int niveauRisque;

    @ManyToOne
    @JoinColumn(name = "id_plan")
    private PlanUrgence planUrgence;

    @OneToMany(mappedBy = "typeIncident", cascade = CascadeType.ALL)
    private List<Incident> incidents;

    public Long getIdTypeIncident() {
        return idTypeIncident;
    }

    public void setIdTypeIncident(Long idTypeIncident) {
        this.idTypeIncident = idTypeIncident;
    }

    public String getNomTypeIncident() {
        return nomTypeIncident;
    }

    public void setNomTypeIncident(String nomTypeIncident) {
        this.nomTypeIncident = nomTypeIncident;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNiveauRisque() {
        return niveauRisque;
    }

    public void setNiveauRisque(int niveauRisque) {
        this.niveauRisque = niveauRisque;
    }

    public PlanUrgence getPlanUrgence() {
        return planUrgence;
    }

    public void setPlanUrgence(PlanUrgence planUrgence) {
        this.planUrgence = planUrgence;
    }

    public List<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<Incident> incidents) {
        this.incidents = incidents;
    }
}
