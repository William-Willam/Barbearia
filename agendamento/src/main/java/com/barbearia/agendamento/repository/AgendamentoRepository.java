package com.barbearia.agendamento.repository;

import com.barbearia.agendamento.model.Agendamento;
import com.barbearia.agendamento.model.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    // Busca agendamentos que já existem entre dois horários, ignorando os cancelados
    List<Agendamento> findByDataHoraBetweenAndStatusNot(
            LocalDateTime inicio, LocalDateTime fim, StatusAgendamento status);
}