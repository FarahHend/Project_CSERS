package Hend.BackendSpringboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long idReservation;

    @Column(name = "reserved_quantity")
    private int reservedQuantity;

    @ManyToOne
    @JoinColumn(name = "id_equipe")
    private EquipeIntervention equipeIntervention;

    @ManyToOne
    @JoinColumn(name = "id_ressource")
    private Ressource ressource;

    // Getters and setters
    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public int getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(int reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    public EquipeIntervention getEquipeIntervention() {
        return equipeIntervention;
    }

    public void setEquipeIntervention(EquipeIntervention equipeIntervention) {
        this.equipeIntervention = equipeIntervention;
    }

    public Ressource getRessource() {
        return ressource;
    }

    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }
}
