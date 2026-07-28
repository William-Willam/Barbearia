package com.barbearia.agendamento.repository;

import com.barbearia.agendamento.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
