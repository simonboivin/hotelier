package fr.sboivin.hotelier.controllers.api;


import fr.sboivin.hotelier.model.hotel.HotelEntity;
import fr.sboivin.hotelier.model.hotel.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/hotel")
public class HotelControllerApi {

    private final HotelService hotelService;

    public HotelControllerApi(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping(path = "", produces = "application/json")
    public Iterable<HotelEntity> getHotelListApi() {
        return hotelService.getHotelList();
    }

    @GetMapping(path = "/{id}")
    public HotelEntity getHotelByIdApi(@PathVariable("id") Integer id) {
        Optional<HotelEntity> hotelOptional = hotelService.getHotelById(id);
        if (hotelOptional.isPresent()) {
            return hotelOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erreur: L'hôtel n'est pas trouvé");
        }
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<HotelEntity> addHotelApi(@RequestBody HotelEntity hotelRequestInput) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                hotelService.addHotel(hotelRequestInput.getNom(),
                        hotelRequestInput.getEtoiles(),
                        hotelRequestInput.getAdresse(),
                        hotelRequestInput.getTelephone(),
                        hotelRequestInput.getEmail(),
                        hotelRequestInput.getVille())
        );
    }


    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<HotelEntity> editHotelApi(@RequestBody HotelEntity hotelRequestInput, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                hotelService.editHotelById(id,
                        hotelRequestInput.getNom(),
                        hotelRequestInput.getEtoiles(),
                        hotelRequestInput.getAdresse(),
                        hotelRequestInput.getTelephone(),
                        hotelRequestInput.getEmail(),
                        hotelRequestInput.getVille())
        );
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity deleteHotelByIdApi(@PathVariable Integer id) {
        hotelService.deleteHotelById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
