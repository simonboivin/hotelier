package fr.sboivin.hotelier.controllers.api;

import fr.sboivin.hotelier.model.client.ClientEntity;
import fr.sboivin.hotelier.model.client.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientControllerApi {

    private final ClientService clientService;

    public ClientControllerApi(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(path = "", produces = "application/json")
    public Iterable<ClientEntity> getClientListApi() {
        return clientService.getClientList();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ClientEntity getClientByIdApi(@PathVariable Integer id) {
        Optional<ClientEntity> clientOptional = clientService.getClientById(id);
        if (clientOptional.isPresent()) {
            return clientOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur: Le client n'est pas trouvé dans la base");
        }
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<ClientEntity> addClientApi(@RequestBody ClientEntity clientRequestInput) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                clientService.addClient(clientRequestInput.getNom(),
                        clientRequestInput.getTelephone(),
                        clientRequestInput.getEmail(),
                        clientRequestInput.getAdresse()
                ));
    }


    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<ClientEntity> editClientApi(@RequestBody ClientEntity clientRequestInput, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                clientService.editClientById(id,
                        clientRequestInput.getNom(),
                        clientRequestInput.getTelephone(),
                        clientRequestInput.getEmail(),
                        clientRequestInput.getAdresse())
        );
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity deleteClientByIdApi(@PathVariable Integer id) {
        clientService.deleteClientById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
