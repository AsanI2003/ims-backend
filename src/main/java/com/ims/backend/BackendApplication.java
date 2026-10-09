package com.ims.backend;

import com.ims.backend.model.User;
import com.ims.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataSeed(UserRepository userRepository, PasswordEncoder encoder) {
		return args -> {


			// Create Admin if not exists
			if (userRepository.findByUsername("admin").isEmpty()) {
				User admin = new User();
				admin.setUsername("admin");
				admin.setPassword(encoder.encode("admin123"));
				admin.setRole("ADMIN");
				userRepository.save(admin);
				System.out.println("Default ADMIN user created admin / admin123");
			}

			// Create Regular User if not exists
			if (userRepository.findByUsername("user").isEmpty()) {
				User regularUser = new User();
				regularUser.setUsername("user");
				regularUser.setPassword(encoder.encode("user123"));
				regularUser.setRole("USER");
				userRepository.save(regularUser);
				System.out.println("Default USER created user / user123");
			}
		};
	}
}
