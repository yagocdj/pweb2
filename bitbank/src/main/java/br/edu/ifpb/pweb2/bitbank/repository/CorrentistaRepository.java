package br.edu.ifpb.pweb2.bitbank.repository;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

// @Component poderia ser usado no lugar - @Repository é um @Component com mais semântica
@Repository
public class CorrentistaRepository {
    private Map<Integer, Correntista> repositorio = new HashMap<Integer, Correntista>();

    public Correntista findById(Integer id) {
        return repositorio.get(id);
    }

    public Correntista save(Correntista correntista) {
        Integer id = null;
        id = (correntista.getId() == null) ? this.getMaxId() : correntista.getId();
        correntista.setId(id);
        repositorio.put(id, correntista);
        return correntista;
    }

    public List<Correntista> findAll() {
        List<Correntista> correntistas = repositorio.values().stream().collect(Collectors.toList());
        return correntistas;
    }

    public Integer getMaxId() {
        List<Correntista> correntistas = findAll();
        if (correntistas == null || correntistas.isEmpty())
            return 1;
        Correntista correntistaMaxId = correntistas
                .stream()
                .max(Comparator.comparing(Correntista::getId))
                .orElseThrow(NoSuchElementException::new);
        return correntistaMaxId.getId() == null ? 1 : correntistaMaxId.getId() + 1;
    }

}