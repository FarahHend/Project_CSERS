package Hend.BackendSpringboot.entity;

import jakarta.persistence.*;


import java.util.Date;
import java.util.List;

@Entity
@Table(name = "claim")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_claim")
    private Long idClaim;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "date")
    private Date date;

    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL)
    private List<Response> responses;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}

