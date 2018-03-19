package com.mgillies.test.mso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.mgillies.test.mso.model.Person;
import com.mgillies.test.mso.repository.PersonRepository;


@SpringBootApplication
public class MgilliesApplication {

	private static final Logger logger = LoggerFactory.getLogger(MgilliesApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MgilliesApplication.class, args);
		logger.info("The API should now be ready to use.");
	}
	
	/*
	@Bean
	public CommandLineRunner setup(PersonRepository personRepo) {
		return (args) -> {
			personRepo.save(new Person("Bloggs", "Joe", "test@xyz.com"));
			personRepo.save(new Person("Smith", "John", "jsmith@xyz.com"));
			personRepo.save(new Person("Black", "Jack", "jb@xyz.com"));
			personRepo.save(new Person("White", "Jane", "jw@xyz.com"));
			
			logger.info("The sample data has been generated");
		};
	}
	*/
	
}

