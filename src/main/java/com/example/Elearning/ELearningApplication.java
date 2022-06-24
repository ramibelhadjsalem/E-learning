package com.example.Elearning;

import com.example.Elearning.Models.SectionModels.ESection;
import com.example.Elearning.Models.SectionModels.Section;
import com.example.Elearning.Models.UserModel.ERole;
import com.example.Elearning.Models.UserModel.Role;
import com.example.Elearning.Repositorys.RoleRpository;
import com.example.Elearning.Repositorys.SubjectRepo.SectionRepository;
import com.example.Elearning.Services.SubjectServices.SectionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ELearningApplication {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(ELearningApplication.class, args);
	}



}
