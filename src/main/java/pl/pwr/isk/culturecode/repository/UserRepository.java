package pl.pwr.isk.culturecode.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import pl.pwr.isk.culturecode.model.UserEntity;

@Component
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}