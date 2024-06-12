package pl.pwr.isk.culturecode.repository;

import pl.pwr.isk.culturecode.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);
    UserEntity findByLoginAndPassword(String login, String password);
    UserEntity findByEmail(String email);
}