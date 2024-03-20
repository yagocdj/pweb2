package br.edu.ifpb.pweb2.bitbank.controller;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.repository.CorrentistaRepository;
import br.edu.ifpb.pweb2.bitbank.service.CorrentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/correntistas")
public class CorrentistaController {

    @Autowired
    private CorrentistaRepository correntistaRepository;

    @Autowired
    private CorrentistaService correntistaService;

    @GetMapping("/form")
    public String getForm(Correntista correntista, Model model) {
        model.addAttribute("correntista", correntista);
        return "correntistas/form";
    }

    @PostMapping("/save")
    public String save(Correntista correntista, Model model) {
        String correntistaName = correntista.getNome();

        // FIXME - this if-else block deserves a REFACTOR!
        if (!correntistaService.isFieldNotBlank(correntistaName)) {
            model.addAttribute("mensagem",
                    "Ops! O campo \"nome\" deve ter algum valor (espaços em branco não contam).");
            return "correntistas/form";
        } else if (!correntistaService.isNameSmallerThan50Chars(correntistaName)) {
            model.addAttribute("mensagem",
                    "Ops! O campo \"nome\" deve ter até 50 caracteres.");
            return "correntistas/form";
        } else if (!correntistaService.isFieldNotBlank(correntista.getEmail())) {
            model.addAttribute("mensagem",
                    "Ops! O campo \"email\" deve ter algum valor (espaços em branco não contam).");
            return "correntistas/form";
        } else if (!correntistaService.isFieldNotBlank(correntista.getSenha())) {
            model.addAttribute("mensagem",
                    "Ops! O campo \"senha\" deve ter algum valor (espaços em branco não contam).");
            return "correntistas/form";
        }

        correntistaRepository.save(correntista);
        model.addAttribute("correntistas", correntistaRepository.findAll());
        return "correntistas/list";
    }

}
