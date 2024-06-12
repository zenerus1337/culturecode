package pl.pwr.isk.culturecode.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.isk.culturecode.DTO.VisitedPlacesDTO;
import pl.pwr.isk.culturecode.model.VisitedPlacesEntity;
import pl.pwr.isk.culturecode.repository.VisitedPlacesRepository;
import pl.pwr.isk.culturecode.service.VisitedPlacesService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/visited")
@RestController
public class VisitedPlacesController {
    private final VisitedPlacesRepository visitedPlacesRepository;
    private final VisitedPlacesService visitedPlacesService;

    @Autowired
    public VisitedPlacesController(VisitedPlacesRepository visitedPlacesRepository, VisitedPlacesService visitedPlacesService) {
        this.visitedPlacesRepository = visitedPlacesRepository;
        this.visitedPlacesService = visitedPlacesService;
    }

    @GetMapping
    List<VisitedPlacesEntity> getAllVisited() {return visitedPlacesRepository.findAll();}


    @PostMapping("/addVisitedPlace")
    public ResponseEntity<List<VisitedPlacesDTO>> addVisited(@RequestBody VisitedPlacesDTO visitedPlacesDTO) {
        List<VisitedPlacesDTO> response = new ArrayList<>();

        VisitedPlacesEntity newVisit = new VisitedPlacesEntity();
        newVisit.setPlace_id(visitedPlacesDTO.getPlace_id());
        newVisit.setUser_id(visitedPlacesDTO.getUser_id());
        newVisit.setTimeStamp(visitedPlacesDTO.getTimeStamp());
        visitedPlacesRepository.save(newVisit);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
