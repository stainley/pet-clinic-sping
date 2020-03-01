package com.salapp.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetClinicApplication {

	protected PetClinicApplication(){}

	public static void main(String[] args) {
		SpringApplication.run(PetClinicApplication.class, args);
	}

}
