package br.edu.ifpb.pweb2.bitbank.service;

import br.edu.ifpb.pweb2.bitbank.model.Conta;
import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import br.edu.ifpb.pweb2.bitbank.repository.ContaRepository;
import br.edu.ifpb.pweb2.bitbank.repository.CorrentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ContaService implements Service<Conta, Integer> {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private CorrentistaRepository correntistaRepository;

    @Override
    public List<Conta> findAll() {
        return contaRepository.findAll();
    }

    @Override
    public Conta findById(Integer id) {
        Conta conta = null;
        Optional<Conta> opConta = contaRepository.findById(id);
        if (opConta.isPresent()) {
            conta = opConta.get();
        }
        return conta;
    }

    @Override
    public Conta save(Conta conta) {
        Optional<Correntista> correntista = correntistaRepository.findById(conta.getCorrentista().getId());
        correntista.ifPresent(conta::setCorrentista);
        // conta.setCorrentista(correntista);
        return contaRepository.save(conta);
    }

    public Conta findByNumeroWithTransacoes(String nuConta) {
        Conta conta = null;
        Optional<Conta> opConta = contaRepository.findByNumeroWithTransacoes(nuConta);
        if (opConta.isPresent()) {
            conta = opConta.get();
        }
        return conta;
    }

    public Conta findByIdWithTransacoes(Integer idConta) {
        Conta conta = null;
        Optional<Conta> opConta = contaRepository.findByIdWithTransacoes(idConta);
        if (opConta.isPresent()) {
            conta = opConta.get();
        }
        return conta;
    }

    public void deleteById(Integer id) {
        contaRepository.deleteById(id);
    }
}
