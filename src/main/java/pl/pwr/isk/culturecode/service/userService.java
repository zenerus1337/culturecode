package pl.pwr.isk.culturecode.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.isk.culturecode.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class userService {
    @Autowired
    protected UserRepository userRepository;

}
