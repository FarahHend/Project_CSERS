package Hend.BackendSpringboot.repository;

import Hend.BackendSpringboot.entity.EquipeIntervention;
import Hend.BackendSpringboot.entity.Reservation;
import Hend.BackendSpringboot.entity.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RessourceRepository extends JpaRepository<Ressource, Long> {
    @Query("SELECT r FROM Ressource r JOIN r.reservations res JOIN res.equipeIntervention e WHERE e.nomEquipe = :equipeName")
    List<Ressource> findByReservationsEquipeInterventionNomEquipe(String equipeName);
}
