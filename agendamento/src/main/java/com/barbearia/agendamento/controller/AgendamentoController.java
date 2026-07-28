package com.barbearia.agendamento.controller;

import com.barbearia.agendamento.model.Agendamento;
import com.barbearia.agendamento.service.AgendamentoService;
import com.barbearia.agendamento.service.ClienteService;
import com.barbearia.agendamento.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("agendamentos", agendamentoService.listarTodos());
        return "agendamentos/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("agendamento", new Agendamento());
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("servicos", servicoService.listarTodos());
        return "agendamentos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("agendamento", agendamentoService.buscarPorId(id));
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("servicos", servicoService.listarTodos());
        return "agendamentos/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Agendamento agendamento, Model model) {
        try {
            agendamentoService.salvar(agendamento);
            return "redirect:/agendamentos";
        } catch (IllegalStateException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("agendamento", agendamento);
            model.addAttribute("clientes", clienteService.listarTodos());
            model.addAttribute("servicos", servicoService.listarTodos());
            return "agendamentos/formulario";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        agendamentoService.excluir(id);
        return "redirect:/agendamentos";
    }
}