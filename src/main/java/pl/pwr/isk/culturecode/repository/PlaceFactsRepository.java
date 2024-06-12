package pl.pwr.isk.culturecode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pwr.isk.culturecode.model.PlaceEntity;
import pl.pwr.isk.culturecode.model.PlaceFactsEntity;

@Repository
public interface PlaceFactsRepository extends JpaRepository<PlaceFactsEntity, Long> {
}
