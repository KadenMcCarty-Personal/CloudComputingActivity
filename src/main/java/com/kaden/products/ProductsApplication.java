package com.kaden.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories //(basePackages = "com.kaden.products.data")
public class ProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
		// hello? OK! it seemed to have worked! I'm going to commit the same file from my other git account now with this comment changed!
	}

}
