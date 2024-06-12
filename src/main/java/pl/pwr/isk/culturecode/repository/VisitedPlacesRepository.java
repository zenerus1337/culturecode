package pl.pwr.isk.culturecode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pwr.isk.culturecode.model.VisitedPlacesEntity;

@Repository
public interface VisitedPlacesRepository extends JpaRepository<VisitedPlacesEntity, Long> {

}
