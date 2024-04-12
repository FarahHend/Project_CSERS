package Hend.BackendSpringboot.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "incident")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_incident")
    private Long idIncident;

    @Column(name = "localisation")
    private String localisation;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private IncidentStatus status;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "equipe_intervention_incident",
            joinColumns = @JoinColumn(name = "id_incident"),
            inverseJoinColumns = @JoinColumn(name = "id_equipe")
    )
    private List<EquipeIntervention> equipesIntervention;

    @ManyToOne
    @JoinColumn(name = "id_typeincident")
    private TypeIncident typeIncident;

    public Long getIdIncident() {
        return idIncident;
    }

    public void setIdIncident(Long idIncident) {
        this.idIncident = idIncident;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<EquipeIntervention> getEquipesIntervention() {
        return equipesIntervention;
    }

    public void setEquipesIntervention(List<EquipeIntervention> equipesIntervention) {
        this.equipesIntervention = equipesIntervention;
    }

    public IncidentStatus getStatus() {
        return status;
    }

    public void setStatus(IncidentStatus status) {
        this.status = status;
    }

}

