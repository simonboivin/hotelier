package fr.sboivin.hotelier.controllers.api;

import fr.sboivin.hotelier.model.client.ClientService;
import fr.sboivin.hotelier.model.hotel.HotelService;
import fr.sboivin.hotelier.model.resa.ResaEntity;
import fr.sboivin.hotelier.model.resa.ResaService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/resa")
public class ResaControllerApi {

    private final ResaService resaService;
    private final ClientService clientService;
    private final HotelService hotelService;

    public ResaControllerApi(ResaService resaService, ClientService clientService, HotelService hotelService) {
        this.resaService = resaService;
        this.clientService = clientService;
        this.hotelService = hotelService;
    }

    @GetMapping(path = "", produces = "application/json")
    public Iterable<ResaEntity> getResaList() {
        return resaService.getResaList();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResaEntity getResaById(@PathVariable Integer id) {
        Optional<ResaEntity> resaOptional = resaService.getResaById(id);
        if (resaOptional.isPresent()) {
            return resaOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur - La réservation n'a pas été trouvée");
        }
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<ResaEntity> addResaApi(@RequestBody ResaEntity resaRequestInput) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                resaService.addResa(
                        clientService.getClientById(resaRequestInput.getClient().getId())
                                .orElseThrow(() -> {
                                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur dans la création de la réservation, le client n'a pas été trouvé");
                                }),
                        hotelService.getHotelById(resaRequestInput.getHotel().getId())
                                .orElseThrow(() -> {
                                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur dans la création de la réservation, l'hôtel n'a pas été trouvé");
                                }),
                        resaRequestInput.getDateDeb(),
                        resaRequestInput.getDateFin(),
                        resaRequestInput.getNum_chambre()
                )
        );
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<ResaEntity> editResaApi(@RequestBody ResaEntity resaRequestInput, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                resaService.editResaById(id,
                        clientService.getClientById(resaRequestInput.getClient().getId())
                                .orElseThrow(() -> {
                                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur dans la création de la réservation, le client n'a pas été trouvé");
                                }),
                        hotelService.getHotelById(resaRequestInput.getHotel().getId())
                                .orElseThrow(() -> {
                                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur dans la création de la réservation, l'hôtel n'a pas été trouvé");
                                }),
                        resaRequestInput.getDateDeb(),
                        resaRequestInput.getDateFin(),
                        resaRequestInput.getNum_chambre()
                )
        );
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity deleteResaByIdApi(@PathVariable Integer id) {
        try {
            resaService.deleteResaById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Suppression impossible - La réservation n'a pas été trouvée");
        }
    }

}
