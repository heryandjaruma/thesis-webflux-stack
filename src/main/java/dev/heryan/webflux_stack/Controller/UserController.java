package dev.heryan.webflux_stack.Controller;

import dev.heryan.webflux_stack.Model.User;
import dev.heryan.webflux_stack.Repository.UserRepository;
import dev.heryan.webflux_stack.WebRequest.SaveUserWebRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/user")
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

    @GetMapping("/user/{id}")
    Mono<User> getUser(@PathVariable String id) {
        log.info("Getting user {}", id);
        return userRepository.findById(id)
                .doOnError(throwable -> log.error(throwable.getMessage(), throwable))
                .doOnSuccess(u -> log.info("User get: {}", u));
    }

    @PutMapping("/user")
    Mono<User> updateUser(@RequestBody SaveUserWebRequest saveUserWebRequest) {
        log.info("Updating user {}", saveUserWebRequest);
        return userRepository
                .save(User.builder()
                        .id(saveUserWebRequest.getId())
                        .username(saveUserWebRequest.getUsername())
                        .name(saveUserWebRequest.getName())
                        .email(saveUserWebRequest.getEmail())
                        .phone(saveUserWebRequest.getPhone())
                        .address(saveUserWebRequest.getAddress())
                        .dateOfBirth(saveUserWebRequest.getDateOfBirth())
                        .build())
                .doOnError(throwable -> log.error(throwable.getMessage(), throwable))
                .doOnSuccess(u -> log.info("User updated: {}", u));
    }

    @DeleteMapping("/user/{id}")
    Mono<Void> deleteUser(@PathVariable String id) {
        log.info("Deleting user {}", id);
        return userRepository.deleteById(id)
                .doOnError(throwable -> log.error(throwable.getMessage(), throwable))
                .doOnSuccess(u -> log.info("User {} deleted", id));
    }
}
