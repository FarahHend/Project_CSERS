package Hend.BackendSpringboot.DTOs;

import Hend.BackendSpringboot.entity.Disponibilite;

import java.util.List;
public class EquipeInterventionDTO {

    private Long idEquipe;
    private String nomEquipe;
    private String description;

    private Disponibilite disponibilite;
    private List<Long> membresIds; // IDs des membres de l'équipe

    public EquipeInterventionDTO() {
        // Constructeur par défaut
    }

    public EquipeInterventionDTO(Long idEquipe, String nomEquipe, String description, Disponibilite disponibilite ) {
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
        this.description = description;
        this.disponibilite = disponibilite;
        //this.membresIds = membresIds;
    }

    // Getters and setters
    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //public List<Long> getMembresIds() {
        //return membresIds;
    //}

   // public void setMembresIds(List<Long> membresIds) {
        //this.membresIds = membresIds;
    //}

    public Disponibilite getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Disponibilite disponibilite) {
        this.disponibilite = disponibilite;
    }
}

