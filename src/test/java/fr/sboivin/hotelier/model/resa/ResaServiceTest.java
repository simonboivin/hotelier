package fr.sboivin.hotelier.model.resa;

import fr.sboivin.hotelier.model.client.ClientEntity;
import fr.sboivin.hotelier.model.client.ClientService;
import fr.sboivin.hotelier.model.hotel.HotelEntity;
import fr.sboivin.hotelier.model.hotel.HotelService;
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
    private ClientService clientService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private ResaService resaService;

    @Test
    void testGetResaListForOneHotel() {
        ClientEntity client1 = clientService.addClient("Alphonse Elric", "+333658554", "alphone.elric@gmail.com", "5 Rue Nicolas Flammel - 3300 Bordeaux");
        HotelEntity hotel1 = hotelService.addHotel("Hôtel Amestris", 4, "42 Rue de la pierre philosophale - 74000 Annecy", "+3344250000", "amestris@hotel.io", "Annecy");
        ResaEntity resa1 = resaService.addResa(client1, hotel1, LocalDate.of(2022, 05, 20), LocalDate.of(2022, 05, 27), 33);
        assertEquals(compteIterable(resaService.getResaListForOneHotel(hotel1.getId())), 1);
        assertTrue(resaService.getResaListForOneHotel(hotel1.getId()).iterator().next().equals(resa1));

    }

    private <T> Integer compteIterable(Iterable<T> iterable) {
        Integer count = 0;
        for (T i : iterable
        ) {
            count++;
        }
        return count;
    }
}