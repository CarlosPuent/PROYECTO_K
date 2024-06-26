package com.proyectoInventario_Kodigo.proyecto_Inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ProyectoInventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoInventarioApplication.class, args);
	}

}
