package pl.pwr.isk.culturecode.web;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.isk.culturecode.DTO.PlaceFactsDTO;
import pl.pwr.isk.culturecode.model.PlaceEntity;
import pl.pwr.isk.culturecode.model.PlaceFactsEntity;
import pl.pwr.isk.culturecode.repository.PlaceFactsRepository;
import pl.pwr.isk.culturecode.repository.PlaceRepository;
import pl.pwr.isk.culturecode.service.PlaceFactsService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequestMapping("/placesFacts")
@RestController
public class PlaceFactsController {
    private final PlaceFactsRepository placeFactsRepository;
    private final PlaceFactsService placeFactsService;

    @Autowired
    public PlaceFactsController(PlaceFactsRepository placeFactsRepository,PlaceFactsService placeFactsService) {
        this.placeFactsRepository = placeFactsRepository;
        this.placeFactsService = placeFactsService;
    }

    @GetMapping
    public List<PlaceFactsEntity> getAllFacts() {return placeFactsRepository.findAll();}

    @PostMapping("/addFact")
    public ResponseEntity<List<PlaceFactsDTO>> addFact(@RequestBody PlaceFactsDTO placeFactsDTO) {
        List<PlaceFactsDTO> response = new ArrayList<>();

        PlaceFactsEntity newFact = new PlaceFactsEntity();
        newFact.setFact(placeFactsDTO.getFact());
        newFact.setPlace_name(placeFactsDTO.getPlace_name());
        placeFactsRepository.save(newFact);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/getFactsByPlaceName/{name}")
    public ResponseEntity<List<PlaceFactsEntity>> getFactsByName(@PathVariable("name") String name) {
        System.out.println("Requested facts for: " + name);
        List<PlaceFactsEntity> result = placeFactsService.findByPlaceName(name);
        if (result != null && !result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            System.out.println("No facts found for: " + name);
            return ResponseEntity.notFound().build();
        }
    }

}
