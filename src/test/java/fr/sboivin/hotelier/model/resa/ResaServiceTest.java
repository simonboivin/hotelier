package fr.sboivin.hotelier.model.resa;

import fr.sboivin.hotelier.model.client.ClientEntity;
import fr.sboivin.hotelier.model.client.ClientService;
import fr.sboivin.hotelier.model.hotel.HotelEntity;
import fr.sboivin.hotelier.model.hotel.HotelService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ResaServiceTest {


    @Autowired
    private  ClientService clientService;

    @Autowired
    private  HotelService hotelService;

    @Autowired
    private  ResaService resaService;

    private  ClientEntity client1;
    private  ClientEntity client2;
    private  HotelEntity hotel1 ;
    private  HotelEntity hotel2 ;
    private  ResaEntity resa1;
    private  ResaEntity resa2;
    private  ResaEntity resa3;

    @BeforeEach
     void beforeEach() {
        client1 = clientService.addClient("Alphonse Elric", "+333658554", "alphone.elric@gmail.com", "5 Rue Nicolas Flammel - 3300 Bordeaux");
        client2 = clientService.addClient("Edouard Elric", "+333658555", "edouard.elric@gmail.com", "5 Rue Nicolas Flammel - 3300 Bordeaux");
        hotel1 = hotelService.addHotel("Hôtel Amestris", 4, "42 Rue de la pierre philosophale - 74000 Annecy", "+3344250000", "amestris@hotel.io", "Annecy");
        hotel2 = hotelService.addHotel("Auberge Du puits", 2, "42 Rue de la pierre philosophale - 74000 Annecy", "+3344250000", "dupuits@hotel.io", "Annecy");
        resa1 = resaService.addResa(client1, hotel1, LocalDate.of(2022, 05, 20), LocalDate.of(2022, 05, 27), 33);
        resa2 = resaService.addResa(client2, hotel2, LocalDate.of(2022, 10, 20), LocalDate.of(2022, 10, 27), 18);
        resa3 = resaService.addResa(client2, hotel2, LocalDate.of(2022, 11, 20), LocalDate.of(2022, 11, 27), 42);
    }

    @AfterEach
    void afterEach(){
        resaService.deleteResaById(resa1.getId());
        resaService.deleteResaById(resa2.getId());
        resaService.deleteResaById(resa3.getId());
        clientService.deleteClientById(client1.getId());
        clientService.deleteClientById(client2.getId());
        hotelService.deleteHotelById(hotel1.getId());
        hotelService.deleteHotelById(hotel2.getId());
    }

    @Test
    void testGetResaListForOneHotel() {
        assertEquals(compteIterable(resaService.getResaListForOneHotel(hotel1.getId())), 1);
        assertTrue(resaService.getResaListForOneHotel(hotel1.getId()).iterator().next().equals(resa1));
    }

    @Test
    void testGetResaListSearchByClient() {
          assertTrue(resaService.getResaListSearchByClient("Ed").iterator().next().equals(resa2));
    }


    private <T> Integer compteIterable(Iterable<T> iterable) {
        Integer count = 0;
        for (T i : iterable
        ) {
            count++;
        }
        return count;
    }

    @Test
    void testGetResaListForOneHotelAndClient() {
        assertTrue(resaService.getResaListSearchByClient("Ed").iterator().next().equals(resa2));
    }
}