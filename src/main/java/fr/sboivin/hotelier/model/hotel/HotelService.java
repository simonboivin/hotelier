package fr.sboivin.hotelier.model.hotel;


import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;


    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    /**
     * Retourne la liste des hôtels
     */
    public Iterable<HotelEntity> getHotelList() {
        return hotelRepository.findAll();
    }

    /**
     * Retourne un hôtel
     *
     * @param id id de l'hôtel
     */
    public Optional<HotelEntity> getHotelById(Integer id) {
        return hotelRepository.findById(id);
    }

    /**
     * Définit les différents attributs d'un hôtel
     * @param hotel Hôtel
     * @param nom       Nom
     * @param etoiles   Nombre d'étoiles (0-5)
     * @param adresse   Adresse
     * @param telephone Numéro de téléphone
     * @param email     Email
     * @param ville     Ville
     * @return hôtel
     */
    private HotelEntity setHotel(HotelEntity hotel, String nom, Integer etoiles, String adresse, String telephone, String email, String ville) {
        hotel.setNom(nom);
        hotel.setEtoiles(etoiles);
        hotel.setAdresse(adresse);
        hotel.setTelephone(telephone);
        hotel.setEmail(email);
        hotel.setVille(ville);
        return hotel;
    }

    /**
     * Créer un nouvel hôtel
     *
     * @param nom       Nom
     * @param etoiles   Nombre d'étoiles (0-5)
     * @param adresse   Adresse
     * @param telephone Numéro de téléphone
     * @param email     Email
     * @param ville     Ville
     * @return hôtel créé
     */
    @Transactional
    public HotelEntity addHotel(String nom, Integer etoiles, String adresse, String telephone, String email, String ville) {
        HotelEntity hotel = setHotel(new HotelEntity(), nom, etoiles, adresse, telephone, email, ville);
        hotelRepository.save(hotel);
        return hotel;
    }

    /**
     * Edite un hotel
     *
     * @param id        Id de l'hôtel à éditer
     * @param nom       Nom
     * @param etoiles   Nombre d'étoiles (0-5)
     * @param adresse   Adresse
     * @param telephone Numéro de téléphone
     * @param email     Email
     * @param ville     Ville
     * @return hôtel édité
     */
    @Transactional
    public HotelEntity editHotelById(Integer id, String nom, Integer etoiles, String adresse, String telephone, String email, String ville) {
        Optional<HotelEntity> hotelOptional = hotelRepository.findById(id);
        if (hotelOptional.isPresent()) {
            HotelEntity hotel = setHotel(hotelOptional.get(),nom, etoiles, adresse, telephone, email, ville);
            hotelRepository.save(hotel);
            return hotel;
        } else {
            throw new ObjectNotFoundException(id, "Edition impossible, l'hôtel n'a pas été trouvé");
        }
    }

    /**
     * Supprime un hotel
     *
     * @param id Id de l'Hôtel
     */
    @Transactional
    public void deleteHotelById(Integer id) {
        Optional<HotelEntity> hotelOptional = hotelRepository.findById(id);
        if (hotelOptional.isPresent()) {
            hotelRepository.delete(hotelOptional.get());
        } else {
            throw new ObjectNotFoundException(id, "Suppression impossible, l'hôtel n'a pas été trouvé");
        }
    }

}
