package com.example.demo;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// Configura Dotenv para que ignore si el archivo .env no se encuentra
		Dotenv dotenv = Dotenv.configure()
				.ignoreIfMissing() // Esta es la clave
				.load();
		// Si dotenv cargó algo (es decir, .env existía), establece las propiedades
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		if (dotenv != null) {
			dotenv.entries().forEach(entry -> {
				// Opcional: podrías verificar si la propiedad ya existe en el sistema
				// para no sobrescribir las variables de entorno de Render.
				// Sin embargo, Spring Boot tiene su propio orden de precedencia,
				// y las variables de entorno del sistema suelen tener mayor prioridad.
				// Por seguridad, puedes añadir una comprobación:
				if (System.getProperty(entry.getKey()) == null && System.getenv(entry.getKey()) == null) {
					System.setProperty(entry.getKey(), entry.getValue());
				}
				// O, si quieres que .env SOBRESCRIBA las de Render (NO RECOMENDADO para este caso):
				// System.setProperty(entry.getKey(), entry.getValue());
			});
		}
		// Inicia la aplicación Spring Boot
		SpringApplication.run(DemoApplication.class, args);
	}
}

