package pl.pwr.isk.culturecode.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.isk.culturecode.model.QuestionEntity;
import pl.pwr.isk.culturecode.model.VisitedPlacesEntity;
import pl.pwr.isk.culturecode.repository.VisitedPlacesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitedPlacesService {
    @Autowired
    protected VisitedPlacesRepository visitedPlacesRepository;

    public List<VisitedPlacesEntity> findVisitedPlaceName(String name) {
        List<VisitedPlacesEntity> visited = findAll();

        List<VisitedPlacesEntity> visitedFound = new ArrayList<>();

        for(VisitedPlacesEntity question : visitedFound) {
            if(question.getPlace_id().toLowerCase().contains(name.toLowerCase())) {
                if(!visitedFound.contains(question)) {
                    visitedFound.add(question);
                }
            }
        }
        return visitedFound;
    }


    public List<VisitedPlacesEntity> findAll() {
        return visitedPlacesRepository.findAll();
    }
}
