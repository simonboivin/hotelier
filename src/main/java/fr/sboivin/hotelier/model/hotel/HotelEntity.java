package fr.sboivin.hotelier.model.hotel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "hotel")
public class HotelEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nom;

    @Column(nullable = false)
    private Integer etoiles;

    @Column()
    private String adresse;

    @Column(length = 12) //format international e.g. +33101010101
    private String telephone;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String ville;

    public Integer getId() {
        return id;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getEtoiles() {
        return etoiles;
    }

    public void setEtoiles(Integer etoiles) {
        this.etoiles = etoiles;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelEntity that = (HotelEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(nom, that.nom) && Objects.equals(etoiles, that.etoiles) && Objects.equals(adresse, that.adresse) && Objects.equals(telephone, that.telephone) && Objects.equals(email, that.email) && Objects.equals(ville, that.ville);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, etoiles, adresse, telephone, email, ville);
    }
}
