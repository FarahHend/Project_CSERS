package Hend.BackendSpringboot.service;

import Hend.BackendSpringboot.DTOs.ReservationDTO;
import Hend.BackendSpringboot.entity.Reservation;
import Hend.BackendSpringboot.entity.Ressource;
import Hend.BackendSpringboot.entity.EquipeIntervention;
import Hend.BackendSpringboot.exception.InsufficientQuantityException;
import Hend.BackendSpringboot.exception.ResourceNotFoundException;
import Hend.BackendSpringboot.repository.ReservationRepository;
import Hend.BackendSpringboot.repository.RessourceRepository;
import Hend.BackendSpringboot.repository.EquipeInterventionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private RessourceRepository ressourceRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EquipeInterventionRepository equipeInterventionRepository;

    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Ressource ressource = ressourceRepository.findById(reservationDTO.getRessourceId())
                .orElseThrow(() -> new ResourceNotFoundException("Ressource not found with ID: " + reservationDTO.getRessourceId()));

        int reservedQuantity = reservationDTO.getReservedQuantity();
        int availableQuantity = ressource.getTotalQuantite();

        if (availableQuantity >= reservedQuantity && reservedQuantity > 0) {
            ressource.setTotalQuantite(availableQuantity - reservedQuantity);
            ressourceRepository.save(ressource);

            Reservation reservation = new Reservation();
            // Map DTO fields to entity
            BeanUtils.copyProperties(reservationDTO, reservation);

            // Set EquipeIntervention entity based on equipeInterventionId
            EquipeIntervention equipeIntervention = equipeInterventionRepository.findById(reservationDTO.getEquipeInterventionId())
                    .orElseThrow(() -> new ResourceNotFoundException("EquipeIntervention not found with ID: " + reservationDTO.getEquipeInterventionId()));
            reservation.setEquipeIntervention(equipeIntervention);

            // Set Ressource entity based on ressourceId
            reservation.setRessource(ressource);

            Reservation savedReservation = reservationRepository.save(reservation);
            // Convert and return saved entity
            ReservationDTO savedDTO = new ReservationDTO();
            savedDTO.setReservedQuantity(savedReservation.getReservedQuantity());
            savedDTO.setEquipeInterventionId(savedReservation.getEquipeIntervention().getIdEquipe());
            savedDTO.setRessourceId(savedReservation.getRessource().getIdRessource());
            return savedDTO;

        } else {
            throw new InsufficientQuantityException("Insufficient quantity: Requested " + reservedQuantity + " but only " + availableQuantity + " available.");
        }
    }

    public void cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with ID: " + id));

        Ressource ressource = reservation.getRessource();
        int cancelledQuantity = reservation.getReservedQuantity();

        ressource.setTotalQuantite(ressource.getTotalQuantite() + cancelledQuantity);
        ressourceRepository.save(ressource);

        reservationRepository.delete(reservation);
    }
}
