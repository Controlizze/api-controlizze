package com.finance;

import com.finance.domain.user.Authority;
import com.finance.domain.user.User;
import com.finance.repo.AuthorityRepo;
import com.finance.repo.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class FinanceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceApiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserRepo userRepo, AuthorityRepo authorityRepo, PasswordEncoder passwordEncoder) {
		return args -> {
			if (userRepo.findByEmail("admin@finance.com").isPresent()) return;
			Authority client = authorityRepo.save(new Authority(null, "CLIENT"));
			Set<Authority> authorities = new HashSet<>();
			authorities.add(client);

			var admin = User.builder()
							.name("Admin")
							.email("admin@finance.com")
							.password(passwordEncoder.encode("password"))
							.authorities(authorities)
							.build();

			userRepo.save(admin);
		};
	}

}
