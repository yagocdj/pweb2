package br.edu.ifpb.pweb2.bitbank.repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;

@Component
public class CorrentistaRepository {
    private Map<Integer, Correntista> repositorio = new HashMap<Integer, Correntista>();

    public Correntista findById(Integer id) {
        return repositorio.get(id);
    }

    public void save(Correntista correntista) {
        Integer id = null;
        id = (correntista.getId() == null) ? this.getMaxId() + 1 : correntista.getId();
        correntista.setId(id);
        repositorio.put(id, correntista);
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