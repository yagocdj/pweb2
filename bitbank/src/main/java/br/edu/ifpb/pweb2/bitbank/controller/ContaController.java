package br.edu.ifpb.pweb2.bitbank.controller;

import br.edu.ifpb.pweb2.bitbank.model.Conta;
import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.repository.ContaRepository;
import br.edu.ifpb.pweb2.bitbank.repository.CorrentistaRepository;
import br.edu.ifpb.pweb2.bitbank.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private CorrentistaRepository correntistaRepository;

    @Autowired
    private ContaService contaService;

    @ModelAttribute("correntistas")
    private List<Correntista> getCorrentistas() {
        return correntistaRepository.findAll();
    }

    @GetMapping("/form")
    public ModelAndView getForm(ModelAndView mv) {
        mv.setViewName("contas/form");
        mv.addObject("conta", new Conta());
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(Conta conta, ModelAndView mv) {
        contaService.save(conta);
        mv.setViewName("contas/list");
        mv.addObject("contas", contaService.findAll());
        return mv;
    }

    @GetMapping("/list")
    public String listAll() {
        return "contas/list";
    }

}
