package pl.pwr.isk.culturecode.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.isk.culturecode.model.UserEntity;
import pl.pwr.isk.culturecode.repository.UserRepository;
import pl.pwr.isk.culturecode.DTO.UserDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/users")
@RestController
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

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDTO loginDTO) {
        UserEntity user = userRepository.findByLoginAndPassword(loginDTO.getLogin(), loginDTO.getPassword());

        Map<String, String> response = new HashMap<>();
        if (user != null) {
            response.put("message", "Login udane");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Nieprawidłowe dane");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserDTO registerDTO) {
        UserEntity existingUserByLogin = userRepository.findByLogin(registerDTO.getLogin());
        UserEntity existingUserByEmail = userRepository.findByEmail(registerDTO.getEmail());

        Map<String, String> response = new HashMap<>();
        if (existingUserByLogin != null || existingUserByEmail != null) {
            response.put("message", "Użytkownik istnieje");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        UserEntity newUser = new UserEntity();
        newUser.setLogin(registerDTO.getLogin());
        newUser.setEmail(registerDTO.getEmail());
        newUser.setPassword(registerDTO.getPassword());
        userRepository.save(newUser);

        response.put("message", "Użytkownik zajestrowany pomyślnie");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}