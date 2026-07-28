package com.barbearia.agendamento.controller;

import com.barbearia.agendamento.model.Servico;
import com.barbearia.agendamento.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("servicos", servicoService.listarTodos());
        return "servicos/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("servico", new Servico());
        return "servicos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        model.addAttribute("servico", servicoService.buscarPorId(id));
        return "servicos/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute Servico servico, BindingResult result) {
        if (result.hasErrors()) {
            return "servicos/formulario";
        }
        servicoService.salvar(servico);
        return "redirect:/servicos";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        servicoService.excluir(id);
        return "redirect:/servicos";
    }
}