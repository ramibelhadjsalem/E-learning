package com.example.Elearning;

import com.example.Elearning.Models.ERole;
import com.example.Elearning.Models.Role;
import com.example.Elearning.Repositorys.RoleRpository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ELearningApplication {
	@Autowired
	RoleRpository roleRpository ;
	public static void main(String[] args) {
		SpringApplication.run(ELearningApplication.class, args);
	}
	/*@Bean
	CommandLineRunner run(){
		return args -> {
			roleRpository.save(new Role(null,ERole.ROLE_ADMIN));
			roleRpository.save(new Role(null,ERole.ROLE_USER));
			roleRpository.save(new Role(null,ERole.ROLE_MODERATOR));

		};
	}*/

}
