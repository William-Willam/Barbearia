package com.barbearia.agendamento.controller;

import com.barbearia.agendamento.model.Cliente;
import com.barbearia.agendamento.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "clientes/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("cliente", clienteService.buscarPorId(id));
        return "clientes/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return "clientes/formulario";
        }
        clienteService.salvar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        clienteService.excluir(id);
        return "redirect:/clientes";
    }
}