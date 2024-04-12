package Hend.BackendSpringboot.repository;

import Hend.BackendSpringboot.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}

