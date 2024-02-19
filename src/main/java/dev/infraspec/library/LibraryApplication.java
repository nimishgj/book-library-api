package dev.infraspec.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("dev.infraspec.library.repositories")
@EntityScan("dev.infraspec.library.entities")
@ComponentScan({"dev.infraspec.library.services", "dev.infraspec.library.repositories","dev.infraspec.library.controllers"})
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

}

