package br.edu.ifpb.pweb2.bitbank.controller;

import br.edu.ifpb.pweb2.bitbank.model.Conta;
import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.repository.ContaRepository;
import br.edu.ifpb.pweb2.bitbank.repository.CorrentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private CorrentistaRepository correntistaRepository;

    @GetMapping("/form")
    public ModelAndView getForm(Conta conta, ModelAndView mv) {
        mv.setViewName("conta/form");
        mv.addObject("conta", conta);
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(ModelAndView mv) {

        return mv;
    }

    @ModelAttribute("correntistas")
    public List<Correntista> getCorrentistas() {
        return correntistaRepository.findAll();
    }
}
