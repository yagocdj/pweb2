package br.edu.ifpb.pweb2.bitbank.controller;

import br.edu.ifpb.pweb2.bitbank.model.Conta;
import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.model.Transacao;
import br.edu.ifpb.pweb2.bitbank.repository.CorrentistaRepository;
import br.edu.ifpb.pweb2.bitbank.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ModelAndView getForm(ModelAndView modelAndView) {
        modelAndView.setViewName("contas/form");
        modelAndView.addObject("conta", new Conta());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView save(Conta conta, ModelAndView modelAndView, RedirectAttributes attr) {
        String operacao = (conta.getId() == null) ? "criada" : "salva";
        contaService.save(conta);
        modelAndView.setViewName("redirect:contas");
        attr.addFlashAttribute("mensagem", "Conta " + operacao + " com sucesso!");
        return modelAndView;
    }

    @GetMapping
    public ModelAndView listAll(ModelAndView model) {
        model.addObject("contas", contaService.findAll());
        model.setViewName("contas/list");
        return model;
    }

    @GetMapping("/{id}")
    public ModelAndView getContaById(@PathVariable(value = "id") Integer id, ModelAndView modelAndView) {
        modelAndView.addObject("conta", contaService.findById(id));
        modelAndView.setViewName("contas/form");
        return modelAndView;
    }

    @GetMapping("/nuconta")
    public String getNuConta() {
        return "contas/operacao";
    }

    @PostMapping("/operacao")
    public String operacaoConta(String nuConta, Transacao transacao, Model model) {
        String proxPagina = "";
        Conta conta = contaService.findByNumeroWithTransacoes(nuConta);
        if (nuConta != null && transacao.getValor() == null) {
            if (conta != null) {
                model.addAttribute("conta", conta);
                model.addAttribute("transacao", transacao);
            } else {
                model.addAttribute("mensagem", "Conta inexistente!");
            }
            proxPagina = "contas/operacao";
        } else {
            conta.addTransacao(transacao);
            contaService.save(conta);
            proxPagina = "redirect:/contas/" + conta.getId() + "/transacoes";
        }
        return proxPagina;
    }

    @GetMapping("/{id}/transacoes")
    public ModelAndView addTransacaoConta(@PathVariable("id") Integer idConta, ModelAndView mv) {
        Conta conta = contaService.findByIdWithTransacoes(idConta);
        mv.addObject("conta", conta);
        mv.setViewName("contas/transacoes");
        return mv;
    }

    @PostMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Integer id,
        ModelAndView modelAndView, RedirectAttributes attr) {
        contaService.deleteById(id);
        attr.addFlashAttribute("mensagem", "Conta removida com sucesso!");
        modelAndView.setViewName("redirect:/contas");
        return modelAndView;
    }
}
