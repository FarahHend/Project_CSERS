package Hend.BackendSpringboot.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "response")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_response")
    private Long idResponse;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private Date date;

    // Define the relationship
    @ManyToOne
    @JoinColumn(name = "id_claim")
    private Claim claim;
}
