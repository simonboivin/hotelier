package fr.sboivin.hotelier.config.init;

import fr.sboivin.hotelier.model.client.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class AddTestEntries {

    /**
     * Ajoute deux clients de test à la base
     */
    @Bean
    public CommandLineRunner addTestClients(ClientService clientService) {
        return args -> {
            clientService.addClient("Nom_" + UUID.randomUUID(), "+33102030405", UUID.randomUUID() + "@test.com", "1 Rue du Test - 99999 Test-les-Bains");
            clientService.addClient("Nom_" + UUID.randomUUID(), "+33506070809", UUID.randomUUID() + "@test.com", "2 Rue du Test - 99999 Test-les-Bains");
        };
    }
}
