package Hend.BackendSpringboot.repository;

import Hend.BackendSpringboot.entity.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembreRepository extends JpaRepository<Membre, Long> {
    List<Membre> findByEquipeIntervention_IdEquipe(Long equipeInterventionId);
    List<Membre> findByNomContainingIgnoreCase(String name);
    List<Membre> findByPosteContainingIgnoreCase(String poste);
    List<Membre> findByEmailContainingIgnoreCase(String email);

}
