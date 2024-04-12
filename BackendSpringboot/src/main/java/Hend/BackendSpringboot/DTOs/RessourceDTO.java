package Hend.BackendSpringboot.DTOs;

public class RessourceDTO {

    private Long idRessource;
    private String nomRessource;
    private String typeRessource;
    private String localisation;
    private int totalQuantite;

    // Constructors
    public RessourceDTO() {
    }

    public RessourceDTO(String nomRessource, String typeRessource, String localisation) {
        this.nomRessource = nomRessource;
        this.typeRessource = typeRessource;
        this.localisation = localisation;
    }

    public RessourceDTO(Long idRessource, String nomRessource, String typeRessource, String localisation, int getTotalQuantite ){
        this.idRessource = idRessource;
        this.nomRessource = nomRessource;
        this.typeRessource = typeRessource;
        this.localisation = localisation;
        this.totalQuantite = totalQuantite;
    }

    // Getters and Setters
    public String getNomRessource() {
        return nomRessource;
    }

    public void setNomRessource(String nomRessource) {
        this.nomRessource = nomRessource;
    }

    public String getTypeRessource() {
        return typeRessource;
    }

    public void setTypeRessource(String typeRessource) {
        this.typeRessource = typeRessource;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public int getTotalQuantite() {
        return totalQuantite;
    }

    public void setTotalQuantite(int totalQuantite) {
        this.totalQuantite = totalQuantite;
    }
}