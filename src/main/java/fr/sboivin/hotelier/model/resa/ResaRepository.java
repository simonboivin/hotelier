package fr.sboivin.hotelier.model.resa;

import fr.sboivin.hotelier.model.hotel.HotelEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Date;

public interface ResaRepository extends CrudRepository<ResaEntity, Integer> {

    public Iterable<ResaEntity> findAllByHotel(HotelEntity hotel);

    public Iterable<ResaEntity> findAllByClient_NomContains (String client);

    public Iterable<ResaEntity> findAllByHotelAndClient_NomContains (HotelEntity hotel, String client);

   public Iterable<ResaEntity> findAllByNumChambreAndHotel (Integer numChambre, HotelEntity hotel);
}
