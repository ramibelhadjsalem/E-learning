package com.example.Elearning;

import com.example.Elearning.Models.UserModel.ERole;
import com.example.Elearning.Models.UserModel.Role;
import com.example.Elearning.Models.UserModel.User;
import com.example.Elearning.Repositorys.RoleRpository;
import com.example.Elearning.Repositorys.UserRepository;
import com.example.Elearning.Services.Userservices.RoleService;
import com.example.Elearning.Services.Userservices.UserService;
import com.example.Elearning.Storage.StorageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@SpringBootApplication
@EnableAsync
public class ELearningApplication {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	@Autowired
	PasswordEncoder encoder;


	public static void main(String[] args) {
		SpringApplication.run(ELearningApplication.class, args);
	}
	@Bean
	CommandLineRunner run(RoleRpository roleRpository , RoleService roleService, StorageService storageService, UserRepository userRepository){
		return args -> {
			storageService.init();
			if (roleRpository.count()<1) {
				roleRpository.save(new Role(null,ERole.ROLE_ADMIN));
				roleRpository.save(new Role(null,ERole.ROLE_PROF));
				roleRpository.save(new Role(null,ERole.ROLE_USER));
			}
			if(!userRepository.existsByUsernameOrEmail("Admin","Admin")){
					User user = new User(
							"Admin",
							"Admin",
							encoder.encode("password")
					);
				Role userRole = roleService.findByName(ERole.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				user.getRoles().add(userRole);
				userRepository.save(user);
			}
		};
	}




}
