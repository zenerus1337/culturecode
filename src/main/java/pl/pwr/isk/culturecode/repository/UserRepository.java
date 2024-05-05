package pl.pwr.isk.culturecode.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.isk.culturecode.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}