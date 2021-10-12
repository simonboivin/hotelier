package fr.sboivin.hotelier.model.resa;

import fr.sboivin.hotelier.model.client.ClientEntity;
import fr.sboivin.hotelier.model.hotel.HotelEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "resa")
public class ResaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client", referencedColumnName = "id", foreignKey = @ForeignKey(name = "Client_resa_FK"), nullable = false)
    private ClientEntity client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel", referencedColumnName = "id", foreignKey = @ForeignKey(name = "hotel_resa_FK"), nullable = false)
    private HotelEntity hotel;


    @Column(nullable = false)
    private LocalDate dateDeb;

    @Column(nullable = false)
    private LocalDate dateFin;

    @Column(nullable = false)
    private Integer numChambre;

    public Integer getId() {
        return id;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

  public HotelEntity getHotel() {
        return hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }

    public LocalDate getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(LocalDate dateDeb) {
        this.dateDeb = dateDeb;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getNumChambre() {
        return numChambre;
    }

    public void setNumChambre(Integer numChambre) {
        this.numChambre = numChambre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResaEntity that = (ResaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(client, that.client) && Objects.equals(hotel, that.hotel) && Objects.equals(dateDeb, that.dateDeb) && Objects.equals(dateFin, that.dateFin) && Objects.equals(numChambre, that.numChambre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, hotel, dateDeb, dateFin, numChambre);
    }
}
