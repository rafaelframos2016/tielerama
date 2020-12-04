package io.github.rafaelframos2016.tielerama.model.repository;

import io.github.rafaelframos2016.tielerama.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
