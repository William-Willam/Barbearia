package com.barbearia.agendamento.service;

import com.barbearia.agendamento.model.Servico;
import com.barbearia.agendamento.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    public Servico buscarPorId(Long id) {
        return servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
    }

    public Servico salvar(Servico servico) {
        return servicoRepository.save(servico);
    }

    public void excluir(Long id) {
        servicoRepository.deleteById(id);
    }
}