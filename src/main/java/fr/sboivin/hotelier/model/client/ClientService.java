package fr.sboivin.hotelier.model.client;

import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Retourne la liste de tous les clients
     */
    public Iterable<ClientEntity> getClientList() {
        return clientRepository.findAll();
    }

    /**
     * Retourne un client
     *
     * @param id Id du client
     */
    public Optional<ClientEntity> getClientById(Integer id) {
        return clientRepository.findById(id);
    }

    /**
     * Définit les paramètres d'un client
     *
     * @param client    Client
     * @param nom       Nom complet
     * @param telephone Numéro de téléphone
     * @param email     Adresse email
     * @param adresse   Adresse postale
     * @return Client
     */
    private ClientEntity setClient(ClientEntity client, String nom, String telephone, String email, String adresse) {
        client.setNom(nom);
        client.setTelephone(telephone);
        client.setEmail(email);
        client.setAdresse(adresse);
        return client;
    }

    /**
     * Créér un nouveau client
     *
     * @param nom       Nom complet
     * @param telephone Numéro de téléphone
     * @param email     Adresse email
     * @param adresse   Adresse postale
     * @return Client
     */
    @Transactional
    public ClientEntity addClient(String nom, String telephone, String email, String adresse) {
        ClientEntity client = setClient(new ClientEntity(), nom, telephone, email, adresse);
        clientRepository.save(client);
        return client;
    }

    /**
     * Edite un client
     *
     * @param id        Id du client
     * @param nom       Nom complet
     * @param telephone Numéro de téléphone
     * @param email     Adresse email
     * @param adresse   Adresse postale
     * @return Client
     */
    @Transactional
    public ClientEntity editClientById(Integer id, String nom, String telephone, String email, String adresse) {
        Optional<ClientEntity> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            ClientEntity client = setClient(clientOptional.get(), nom, telephone, email, adresse);
            clientRepository.save(client);
            return client;
        } else {
            throw new ObjectNotFoundException(id, "Edition du client impossible, client non trouvé dans la base");
        }
    }

    /**
     * Supprime un client dans la base
     *
     * @param id Id du client à supprimer
     */
    @Transactional
    public void deleteClientById(Integer id) {
        Optional<ClientEntity> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            clientRepository.delete(clientOptional.get());
        } else {
            throw new ObjectNotFoundException(id, "Suppression du client impossible, client non trouvé dans la base");
        }
    }

}
