package br.com.config;

import java.util.HashMap;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		HashMap<String, Object> props = new HashMap<>();
		props.put("server.port", 8180); 
		props.put("server.contextPath", "/petshopCuidarRest"); 

		new SpringApplicationBuilder().sources(Application.class).properties(props).run(args);

		// SpringApplication.run(Application.class, args);
	}

}
