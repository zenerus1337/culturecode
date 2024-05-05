package pl.pwr.isk.culturecode.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pwr.isk.culturecode.model.UserEntity;
import pl.pwr.isk.culturecode.repository.UserRepository;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;
    @Autowired
    public UserController(UserRepository repository) {
        this.userRepository = repository;
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}