package com.example.Elearning;

import com.example.Elearning.Models.UserModel.ERole;
import com.example.Elearning.Models.UserModel.Role;
import com.example.Elearning.Repositorys.RoleRpository;
import com.example.Elearning.Storage.StorageService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ELearningApplication {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(ELearningApplication.class, args);
	}
	@Bean
	CommandLineRunner run(RoleRpository roleRpository , StorageService storageService){
		return args -> {
			storageService.init();
			if (roleRpository.count()<1) {
				roleRpository.save(new Role(null,ERole.ROLE_ADMIN));
				roleRpository.save(new Role(null,ERole.ROLE_PROF));
				roleRpository.save(new Role(null,ERole.ROLE_USER));
			}
		};
	}




}
