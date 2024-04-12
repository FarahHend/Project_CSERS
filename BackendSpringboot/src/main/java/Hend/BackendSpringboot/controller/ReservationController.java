package Hend.BackendSpringboot.controller;

import Hend.BackendSpringboot.DTOs.ReservationDTO;
import Hend.BackendSpringboot.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/make")
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        ReservationDTO createdReservation = reservationService.createReservation(reservationDTO);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.ok("Reservation canceled successfully.");
    }
}
