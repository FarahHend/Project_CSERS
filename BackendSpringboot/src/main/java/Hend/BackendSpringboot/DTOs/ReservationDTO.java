package Hend.BackendSpringboot.DTOs;

public class ReservationDTO {

    private Long idReservation;
    private int reservedQuantity;
    private Long equipeInterventionId;
    private Long ressourceId;

    public ReservationDTO(int reservedQuantity, Long equipeInterventionId, Long ressourceId) {
        this.reservedQuantity = reservedQuantity;
        this.equipeInterventionId = equipeInterventionId;
        this.ressourceId = ressourceId;}

    public ReservationDTO() {

    }

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

    public Long getEquipeInterventionId() {
        return equipeInterventionId;
    }

    public void setEquipeInterventionId(Long equipeInterventionId) {
        this.equipeInterventionId = equipeInterventionId;
    }

    public Long getRessourceId() {
        return ressourceId;
    }

    public void setRessourceId(Long ressourceId) {
        this.ressourceId = ressourceId;
    }
}
