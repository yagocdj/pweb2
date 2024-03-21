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
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView save(Correntista correntista, ModelAndView mv) {
        String correntistaName = correntista.getNome();
        mv.setViewName("correntistas/list");

        // FIXME - this if-else block deserves a REFACTOR!
        if (!correntistaService.isFieldNotBlank(correntistaName)) {
            mv.addObject("mensagem",
                    "Ops! O campo \"nome\" deve ter algum valor (espaços em branco não contam).");
            mv.setViewName("correntistas/form");
        } else if (!correntistaService.isNameSmallerThan50Chars(correntistaName)) {
            mv.addObject("mensagem",
                    "Ops! O campo \"nome\" deve ter até 50 caracteres.");
            mv.setViewName("correntistas/form");
        } else if (!correntistaService.isFieldNotBlank(correntista.getEmail())) {
            mv.addObject("mensagem",
                    "Ops! O campo \"email\" deve ter algum valor (espaços em branco não contam).");
            mv.setViewName("correntistas/form");
        } else if (!correntistaService.isFieldNotBlank(correntista.getSenha())) {
            mv.addObject("mensagem",
                    "Ops! O campo \"senha\" deve ter algum valor (espaços em branco não contam).");
            mv.setViewName("correntistas/form");
        }

        correntistaRepository.save(correntista);
        mv.addObject("correntistas", correntistaRepository.findAll());
        return mv;
    }

}
