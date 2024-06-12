package pl.pwr.isk.culturecode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.isk.culturecode.model.PlaceEntity;
import pl.pwr.isk.culturecode.model.PlaceFactsEntity;
import pl.pwr.isk.culturecode.repository.PlaceFactsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceFactsService {
    @Autowired
    protected PlaceFactsRepository placeFactsRepository;

    public List<PlaceFactsEntity> findByPlaceName(String name) {
        List<PlaceFactsEntity> places = placeFactsRepository.findAll();

        List<PlaceFactsEntity> factsFound = new ArrayList<>();

        for(PlaceFactsEntity place : places) {
            if(place.getPlace_name().toLowerCase().equals(name.toLowerCase())) {
                factsFound.add(place);
            }
        }
        return factsFound;
    }
}
