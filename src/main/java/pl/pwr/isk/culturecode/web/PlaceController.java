package pl.pwr.isk.culturecode.web;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.isk.culturecode.DTO.PlaceDTO;
import pl.pwr.isk.culturecode.model.PlaceEntity;
import pl.pwr.isk.culturecode.repository.PlaceRepository;
import pl.pwr.isk.culturecode.service.PlacesService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RequestMapping("/places")
@RestController
public class PlaceController {

    private final PlaceRepository placeRepository;
    private final PlacesService placesService;

    @Autowired
    public PlaceController(PlaceRepository placeRepository, PlacesService placesService) {
        this.placeRepository = placeRepository;
        this.placesService = placesService;
    }

    @GetMapping
    public List<PlaceEntity> getAllPlaces() {return placeRepository.findAll();}

    @PostMapping("/addPlace")
    public ResponseEntity<List<PlaceDTO>> addPlace(@RequestBody PlaceDTO placeDTO) {
        List<PlaceDTO> response = new ArrayList<>();

        PlaceEntity exists = placeRepository.findByName(placeDTO.getName());

        if(exists != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        PlaceEntity newPlace = new PlaceEntity();
        newPlace.setName(placeDTO.getName());
        newPlace.setDescription(placeDTO.getDescription());
        newPlace.setLang(placeDTO.getLang());
        newPlace.setLat(placeDTO.getLat());
        newPlace.setQr_code_hint(placeDTO.getQrCodeHint());
        newPlace.setQrCode(placeDTO.getQrCode());

        placeRepository.save(newPlace);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/place/{name}")
    public ResponseEntity<List<PlaceEntity>> getPlace(@ApiParam("name") @RequestParam String name) {
        PlaceEntity result = placeRepository.findByName(name);
        if (result != null) {
            return ResponseEntity.ok(Collections.singletonList(result));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/place/description/{name}")
    public ResponseEntity<String> getPlaceDescription(@PathVariable("name") String name) {
        PlaceEntity result = placeRepository.findByName(name);
        if (result != null) {
            return ResponseEntity.ok(result.getDescription());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/place/lat/{name}")
    public ResponseEntity<String> getPlaceLat(@ApiParam("name") @RequestParam String name) {
        PlaceEntity result = placeRepository.findByName(name);
        if (result != null) {
            return ResponseEntity.ok(result.getLat());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/place/lang/{name}")
    public ResponseEntity<String> getPlaceLang(@ApiParam("name") @RequestParam String name) {
        PlaceEntity result = placeRepository.findByName(name);
        if (result != null) {
            return ResponseEntity.ok(result.getLang());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/place/qrCodeCheck/{qrCode}")
    public ResponseEntity<String> getQrCode(@PathVariable("qrCode") String qrCode) {
        PlaceEntity result = placesService.findByQrCode(qrCode);
        if (result != null) {
            return ResponseEntity.ok(result.getName());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
