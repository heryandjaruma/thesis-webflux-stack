package dev.heryan.webflux_stack.Repository;

import dev.heryan.webflux_stack.Model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
