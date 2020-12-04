package io.github.rafaelframos2016.tielerama;

import io.github.rafaelframos2016.tielerama.model.entity.Cliente;
import io.github.rafaelframos2016.tielerama.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TieleramaApplication {

	@Bean
	public CommandLineRunner run(@Autowired ClienteRepository repository){
		return args -> {
			Cliente cliente  = Cliente.builder().cpf("00000000000").nome("Rafael").build();
			repository.save(cliente);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(TieleramaApplication.class, args);
	}

}
