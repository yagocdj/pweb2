package br.edu.ifpb.pweb2.bitbank.repository;

import br.edu.ifpb.pweb2.bitbank.model.Conta;
import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ContaRepository {
    private Map<Integer, Conta> repositorio = new HashMap<Integer, Conta>();

    public Conta findById(Integer id) {
        return repositorio.get(id);
    }

    public Conta save(Conta conta) {
        Integer id = null;
        id = (conta.getId() == null) ? this.getMaxId() + 1 : conta.getId();
        conta.setId(id);
        repositorio.put(id, conta);
        return conta;
    }

    public List<Conta> findAll() {
        List<Conta> contas = repositorio.values().stream().collect(Collectors.toList());
        return contas;
    }

    public Integer getMaxId() {
        List<Conta> contas = findAll();
        if (contas == null || contas.isEmpty())
            return 1;
        Conta contaMaxId = contas
                .stream()
                .max(Comparator.comparing(Conta::getId))
                .orElseThrow(NoSuchElementException::new);
        return contaMaxId.getId() == null ? 1 : contaMaxId.getId() + 1;
    }
}
