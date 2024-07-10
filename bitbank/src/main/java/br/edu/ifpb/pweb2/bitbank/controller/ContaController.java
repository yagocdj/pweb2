package br.edu.ifpb.pweb2.bitbank.controller;

import br.edu.ifpb.pweb2.bitbank.model.Conta;
import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.model.Transacao;
import br.edu.ifpb.pweb2.bitbank.repository.ContaRepository;
import br.edu.ifpb.pweb2.bitbank.repository.CorrentistaRepository;
import br.edu.ifpb.pweb2.bitbank.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private CorrentistaRepository correntistaRepository;

    @Autowired
    private ContaService contaService;
    @Autowired
    private ContaRepository contaRepository;

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

    @GetMapping("/nuconta")
    public String getNuConta() {
        return "contas/operacao";
    }

    @PostMapping("/operacao")
    public String operacaoConta(String nuConta, Transacao transacao, ModelAndView mv) {
        String proxPagina;
        // if there is no value for the transaction
        if (nuConta != null && transacao.getValor() == null) {
            Conta conta = contaService.findByNumeroWithTransacoes(nuConta);
            if (conta != null) {
                mv.addObject("conta", conta);
                mv.addObject("transacao", transacao);
            } else {
                mv.addObject("mensagem", "Conta inexistente!");
            }
            proxPagina = "contas/operacao";
        } else {
            Conta conta = contaService.findByNumeroWithTransacoes(nuConta);
            conta.addTransacao(transacao);
            contaService.save(conta);
            proxPagina = addTransacaoConta(conta.getId(), mv);
        }
        return proxPagina;
    }

    @GetMapping("/{id}/transacoes")
    public String addTransacaoConta(@PathVariable("id") Integer idConta, ModelAndView mv) {
        Conta conta = contaService.findByIdWithTransacoes(idConta);
        mv.addObject("conta", conta);
        return "contas/transacoes";
    }
}
