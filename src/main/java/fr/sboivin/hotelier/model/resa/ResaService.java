package fr.sboivin.hotelier.model.resa;

import fr.sboivin.hotelier.model.client.ClientEntity;
import fr.sboivin.hotelier.model.hotel.HotelEntity;
import fr.sboivin.hotelier.model.hotel.HotelService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ResaService {

    private final ResaRepository resaRepository;
    private final HotelService hotelService;

    public ResaService(ResaRepository resaRepository, HotelService hotelService) {
        this.resaRepository = resaRepository;
        this.hotelService = hotelService;
    }

    /**
     * Obtenir la liste de toutes les réservations
     */
    public Iterable<ResaEntity> getResaList() {
        return resaRepository.findAll();
    }

    /**
     * Obtenir une réservation
     *
     * @param id Id de la réservation
     */
    public Optional<ResaEntity> getResaById(Integer id) {
        return resaRepository.findById(id);
    }

    /**
     * Obtenir la liste des réservations pour un hôtel
     *
     * @param hotelId  Id de l'Hôtel
     */
    public Iterable<ResaEntity> getResaListForOneHotel(Integer hotelId) {
        return resaRepository.findAllByHotel(hotelService.getHotelById(hotelId).orElse(null));
    }

    /**
     * Paramètre une réservation
     *
     * @param resa        Réservation à paramétrer
     * @param client      Client faisant la réservation
     * @param hotel       Hotel concerné par la réservation
     * @param dateDeb     Date d'arrivée du client
     * @param dateFin     Date de départ du client
     * @param num_chambre Numéro de la chambre réservée
     * @return Réservation
     */
    public ResaEntity setResa(ResaEntity resa, ClientEntity client, HotelEntity hotel, LocalDate dateDeb, LocalDate dateFin, Integer num_chambre) {
        resa.setClient(client);
        resa.setHotel(hotel);
        resa.setDateDeb(dateDeb);
        resa.setDateFin(dateFin);
        resa.setNum_chambre(num_chambre);
        return resa;
    }

    /**
     * Créér une nouvelle réservation
     *
     * @param client      Client faisant la réservation
     * @param hotel       Hotel concerné par la réservation
     * @param dateDeb     Date d'arrivée du client
     * @param dateFin     Date de départ du client
     * @param num_chambre Numéro de la chambre réservée
     * @return Réservation
     */
    public ResaEntity addResa(ClientEntity client, HotelEntity hotel, LocalDate dateDeb, LocalDate dateFin, Integer num_chambre) {
        ResaEntity resa = setResa(new ResaEntity(), client, hotel, dateDeb, dateFin, num_chambre);
        resaRepository.save(resa);
        return resa;
    }

    /**
     * Edite une réservation
     *
     * @param id          Id de la réservation à éditer
     * @param client      Client faisant la réservation
     * @param hotel       Hotel concerné par la réservation
     * @param dateDeb     Date d'arrivée du client
     * @param dateFin     Date de départ du client
     * @param num_chambre Numéro de la chambre réservée
     * @return Réservation
     */
    public ResaEntity editResaById(Integer id, ClientEntity client, HotelEntity hotel, LocalDate dateDeb, LocalDate dateFin, Integer num_chambre) {
        Optional<ResaEntity> resaOptional = resaRepository.findById(id);
        if (resaOptional.isPresent()) {
            ResaEntity resa = setResa(resaOptional.get(), client, hotel, dateDeb, dateFin, num_chambre);
            resaRepository.save(resa);
            return resa;
        } else {
            throw new ObjectNotFoundException(id, "Edition de la réservation impossible: la réservation n'a pas été trouvée dans la base");
        }
    }

    /**
     * Supprime une réservation
     *
     * @param id Id de la réservation à supprimer
     */
    public void deleteResaById(Integer id) {
        Optional<ResaEntity> resaOptional = resaRepository.findById(id);
        if (resaOptional.isPresent()) {
            resaRepository.delete(resaOptional.get());
        } else {
            throw new ObjectNotFoundException(id, "Suppression de la réservation impossible: la réservation n'a pas été trouvée dans la base");
        }
    }

}
