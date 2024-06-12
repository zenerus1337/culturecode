package pl.pwr.isk.culturecode.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.isk.culturecode.model.PlaceEntity;
import pl.pwr.isk.culturecode.repository.PlaceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlacesService {

    @Autowired
    protected PlaceRepository placeRepository;


    public PlaceEntity findByQrCode(String code) {
        List<PlaceEntity> places = findAll();

        for(PlaceEntity place : places) {
            if(place.getQrCode().toLowerCase().equals(code.toLowerCase())) {
                return place;
            }
        }
        return null;
    }

    public PlaceEntity getPlaceByQrCode(String qrCode) {
        PlaceEntity place = placeRepository.findByQrCode(qrCode);
        if (place != null) {
            return new PlaceEntity();
        } else {
            return null;
        }
    }

    public List<PlaceEntity> findAll() {
        return placeRepository.findAll();
    }
}
