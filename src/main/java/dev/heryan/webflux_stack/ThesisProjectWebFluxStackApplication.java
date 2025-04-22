package dev.heryan.webflux_stack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ThesisProjectWebFluxStackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThesisProjectWebFluxStackApplication.class, args);
	}

}
