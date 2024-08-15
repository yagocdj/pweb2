package br.edu.ifpb.pweb2.bitbank.controller;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.service.ContaService;
import br.edu.ifpb.pweb2.bitbank.service.CorrentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/correntistas")
public class CorrentistaController {

    @Autowired
    private CorrentistaService correntistaService;

    @GetMapping("/form")
    public String getForm(Correntista correntista, ModelAndView model) {
        model.addObject("correntista", correntista);
        return "correntistas/form";
    }

    @GetMapping
    public ModelAndView listAll(ModelAndView model) {
        model.addObject("correntistas", correntistaService.findAll());
        model.setViewName("correntistas/list");
        return model;
    }

    @PostMapping
    public ModelAndView save(Correntista correntista, ModelAndView model, RedirectAttributes redirectAttributes) {
        // TODO - create validations for this operation (SERVICE)
        String action = (correntista.getId() == null) ? "criada" : "salva";
        correntistaService.save(correntista);
        redirectAttributes.addFlashAttribute("mensagem", "Conta " + action + " com sucesso!");
        model.setViewName("redirect:/correntistas");
        return model;
    }

    @PostMapping("/{id}/delete")
    public ModelAndView deleteById(@PathVariable(value = "id") Integer id,
                                   ModelAndView modelAndView, RedirectAttributes attr) {
        correntistaService.deleteById(id);
        attr.addFlashAttribute("mensagem", "Correntista removido com sucesso");
        modelAndView.setViewName("redirect:/correntistas");
        return modelAndView;
    }
}
