package com.barbearia.agendamento.service;

import com.barbearia.agendamento.model.Cliente;
import com.barbearia.agendamento.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // listar os clientes
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    // Update
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    // create
    public Cliente salvar( Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // delete
    public void excluir(long id) {
        clienteRepository.deleteById(id);
    }
}
