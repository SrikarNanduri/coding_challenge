package com.example.codingchallenge;

import com.example.codingchallenge.shopping.ProductsController;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CodingChallengeApplication implements CommandLineRunner {

	@Bean
	public ProductsController productsController(){
		return new ProductsController();
	}


	@Override
	public void run(String... args) {
		ProductsController productsController = productsController();
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CodingChallengeApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

}
