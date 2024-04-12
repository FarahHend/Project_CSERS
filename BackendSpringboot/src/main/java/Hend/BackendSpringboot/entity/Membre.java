package Hend.BackendSpringboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "membre_equipe")
public class Membre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_membre")
    private Long idMembre;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(nullable = false)
    private byte[] imageFile;

    private String imageName;
    @Column(name = "email")
    private String email;

    @Column(name = "poste")
    private String poste;

    @Column(name = "number")
    private String number;

    @Column(name = "competences_techniques")
    private String competencesTechniques;

    @Column(name = "certifications")
    private String certifications;

    @Column(name = "experience")
    private int experience;

    @ManyToOne
    @JoinColumn(name = "id_equipe")
    private EquipeIntervention equipeIntervention;

    // Getters and setters
    public Long getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(Long idMembre) {
        this.idMembre = idMembre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCompetencesTechniques() {
        return competencesTechniques;
    }

    public void setCompetencesTechniques(String competencesTechniques) {
        this.competencesTechniques = competencesTechniques;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public EquipeIntervention getEquipeIntervention() {
        return equipeIntervention;
    }

    public void setEquipeIntervention(EquipeIntervention equipeIntervention) {
        this.equipeIntervention = equipeIntervention;
    }

    public byte[] getImageFile() {
        return imageFile;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }
}


