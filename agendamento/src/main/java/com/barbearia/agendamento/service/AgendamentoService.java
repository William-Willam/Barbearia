package com.barbearia.agendamento.service;

import com.barbearia.agendamento.model.Agendamento;
import com.barbearia.agendamento.model.StatusAgendamento;
import com.barbearia.agendamento.repository.AgendamentoRepository;
import com.barbearia.agendamento.repository.ClienteRepository;
import com.barbearia.agendamento.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    public Agendamento buscarPorId(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    public Agendamento salvar(Agendamento agendamento) {
        // Busca os objetos completos no banco, usando apenas o ID que veio do formulário
        agendamento.setCliente(clienteRepository.findById(agendamento.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado")));
        agendamento.setServico(servicoRepository.findById(agendamento.getServico().getId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado")));

        validarDataFutura(agendamento);
        validarConflitoDeHorario(agendamento);

        return agendamentoRepository.save(agendamento);
    }

    public void excluir(Long id) {
        agendamentoRepository.deleteById(id);
    }

    private void validarDataFutura(Agendamento agendamento) {
        if (agendamento.getDataHora().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException(
                    "Não é possível agendar em uma data/hora que já passou.");
        }
    }

    private void validarConflitoDeHorario(Agendamento novoAgendamento) {
        LocalDateTime inicioNovo = novoAgendamento.getDataHora();
        int duracao = novoAgendamento.getServico().getDuracaoMinutos();
        LocalDateTime fimNovo = inicioNovo.plusMinutes(duracao);

        // Busca agendamentos ativos num intervalo maior (janela de segurança)
        List<Agendamento> existentes = agendamentoRepository.findByDataHoraBetweenAndStatusNot(
                inicioNovo.minusHours(4), fimNovo.plusHours(4), StatusAgendamento.CANCELADO);

        for (Agendamento existente : existentes) {
            // Ignora ele mesmo, caso seja uma edição
            if (existente.getId() != null && existente.getId().equals(novoAgendamento.getId())) {
                continue;
            }

            LocalDateTime inicioExistente = existente.getDataHora();
            LocalDateTime fimExistente = inicioExistente.plusMinutes(
                    existente.getServico().getDuracaoMinutos());

            boolean seSobrepoe = inicioNovo.isBefore(fimExistente) && inicioExistente.isBefore(fimNovo);

            if (seSobrepoe) {
                throw new IllegalStateException(
                        "Conflito de horário: já existe um agendamento das " +
                                inicioExistente.toLocalTime() + " às " + fimExistente.toLocalTime() +
                                ". Escolha outro horário.");
            }
        }
    }
}