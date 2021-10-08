package fr.sboivin.hotelier.config.init;

import fr.sboivin.hotelier.model.client.ClientEntity;
import fr.sboivin.hotelier.model.client.ClientService;
import fr.sboivin.hotelier.model.hotel.HotelEntity;
import fr.sboivin.hotelier.model.hotel.HotelService;
import fr.sboivin.hotelier.model.resa.ResaService;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.UUID;

@Configuration
public class AddTestEntries {

    /**
     * Ajoute deux clients, un hôtel et trois réservations de test à la base
     */
    @Bean
    public CommandLineRunner addEntréesDeTest(ClientService clientService, HotelService hotelService, ResaService resaService) {
        return args -> {
            ClientEntity client1 = clientService.addClient("Nom_" + UUID.randomUUID(), "+33102030405", UUID.randomUUID() + "@test.com", "1 Rue du Test - 99999 Test-les-Bains");
            ClientEntity client2 = clientService.addClient("Nom_" + UUID.randomUUID(), "+33506070809", UUID.randomUUID() + "@test.com", "2 Rue du Test - 99999 Test-les-Bains");
            HotelEntity hotel1 = hotelService.addHotel("Hôtel Napoléon", 3, "5 Avenue Napoléon - 13000 Marseille", "+33442000000", "napoleon.marseille@hotels.io", "Marseille");
            resaService.addResa(client1, hotel1, LocalDate.of(2021,12,24), LocalDate.of(2021,12,26),42);
            resaService.addResa(client2, hotel1, LocalDate.of(2021,12,24), LocalDate.of(2021,12,26),99);
            resaService.addResa(client1, hotel1, LocalDate.of(2022,02,10), LocalDate.of(2021,03,4),42);
        };
    }

}
