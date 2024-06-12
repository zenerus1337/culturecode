package pl.pwr.isk.culturecode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  pl.pwr.isk.culturecode.model.PlaceEntity;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceEntity, Long> {
    PlaceEntity findByName(String name);

    PlaceEntity findByQrCode(String qrCode);

}
