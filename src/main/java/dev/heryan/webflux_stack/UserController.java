package dev.heryan.webflux_stack;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Date;

@Slf4j
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    String helloWorld() {
        return new Date().toString();
    }

    @PostMapping
    Mono<User> saveUser(@RequestBody SaveUserWebRequest saveUserWebRequest) {
        log.info("Saving user {}", saveUserWebRequest);
        return userRepository.save(User.builder()
                .id(saveUserWebRequest.getId())
                .username(saveUserWebRequest.getUsername())
                .name(saveUserWebRequest.getName())
                .email(saveUserWebRequest.getEmail())
                .phone(saveUserWebRequest.getPhone())
                .address(saveUserWebRequest.getAddress())
                .dateOfBirth(saveUserWebRequest.getDateOfBirth())
                .build())
                .doOnError(throwable -> log.error(throwable.getMessage(), throwable))
                .doOnSuccess(u -> log.info("User saved: {}", u));
    }
}
