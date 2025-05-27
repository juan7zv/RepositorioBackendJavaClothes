package com.example.demo;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// Configura Dotenv para que ignore si el archivo .env no se encuentra
		Dotenv dotenv = Dotenv.configure()
				.ignoreIfMissing()
				.load();

		// Establece las propiedades solo si no existen como variables de entorno
		dotenv.entries().forEach(entry -> {
			if (System.getProperty(entry.getKey()) == null &&
					System.getenv(entry.getKey()) == null) {
				System.setProperty(entry.getKey(), entry.getValue());
			}
		});

		// Inicia la aplicación Spring Boot
		SpringApplication.run(DemoApplication.class, args);
	}
}
