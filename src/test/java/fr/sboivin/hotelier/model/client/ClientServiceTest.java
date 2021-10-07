package fr.sboivin.hotelier.model.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    private String randomNoTelephone(){
        return "+33"+UUID.randomUUID().toString().substring(0,9);
    }

    private Integer compteIterableElement(Iterable<ClientEntity> iterable) {
        Integer count = 0;
        for (ClientEntity i : iterable
        ) {
            count++;
        }
        return count;
    }

    @Test
    void testGetClientById() {
        ClientEntity client = clientService.addClient("Nom" + UUID.randomUUID(), "+33"+UUID.randomUUID().toString().substring(0,9), UUID.randomUUID() + "@test.com", "1 Rue du Test - 99999 Test-les-Bains");
        assertEquals(client, clientService.getClientById(client.getId()).get());
    }

    @Test
    void testAddClient() {
        Integer nombreClientInitial = compteIterableElement(clientService.getClientList());
        clientService.addClient("Nom" + UUID.randomUUID(), randomNoTelephone(), UUID.randomUUID() + "@test.com", "1 Rue du Test - 99999 Test-les-Bains");
        assertEquals(nombreClientInitial + 1, compteIterableElement(clientService.getClientList()));
    }

    @Test
    void testEditClientById() {
        ClientEntity client = clientService.addClient("Nom" + UUID.randomUUID(), randomNoTelephone(), UUID.randomUUID() + "@test.com", "1 Rue du Test - 99999 Test-les-Bains");
        String nouveauNom = "Ceci_est_un_nom_pour_un_test";
        clientService.editClientById(client.getId(), nouveauNom, client.getTelephone(), client.getEmail(), client.getAdresse());
        assertEquals(nouveauNom, clientService.getClientById(client.getId()).get().getNom());
    }

    @Test
    void testDeleteClientById() {
        ClientEntity client = clientService.addClient("Nom" + UUID.randomUUID(), randomNoTelephone(), UUID.randomUUID() + "@test.com", "1 Rue du Test - 99999 Test-les-Bains");
        clientService.deleteClientById(client.getId());
        assertFalse(clientService.getClientById(client.getId()).isPresent());
    }
}