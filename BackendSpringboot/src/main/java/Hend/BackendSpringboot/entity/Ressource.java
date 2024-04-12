package Hend.BackendSpringboot.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "ressource")
public class Ressource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ressource")
    private Long idRessource;

    @Column(name = "nom_ressource")
    private String nomRessource;

    @Column(name = "type_ressource")
    private String typeRessource;

    @Column(name = "localisation")
    private String localisation;

    @Column(name = "disponibilite")
    private boolean disponibilite;

    @Column(name = "total_quantite")
    private int totalQuantite;

    @OneToMany(mappedBy = "ressource", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    // Getters and setters
    public Long getIdRessource() {
        return idRessource;
    }

    public void setIdRessource(Long idRessource) {
        this.idRessource = idRessource;
    }

    public String getNomRessource() {
        return nomRessource;
    }

    public void setNomRessource(String nomRessource) {
        this.nomRessource = nomRessource;
    }

    public String getTypeRessource() {
        return typeRessource;
    }

    public void setTypeRessource(String typeRessource) {
        this.typeRessource = typeRessource;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public int getTotalQuantite() {
        return totalQuantite;
    }

    public void setTotalQuantite(int totalQuantite) {
        this.totalQuantite = totalQuantite;
    }

    // Other getters and setters for the list of reservations
}
