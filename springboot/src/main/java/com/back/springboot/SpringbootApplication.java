package com.back.springboot;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootApplication {


	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}
