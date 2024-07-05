package br.edu.ifpb.pweb2.bitbank.repository;

import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

// @Component poderia ser usado no lugar - @Repository é um @Component com mais semântica
@Repository
public interface CorrentistaRepository extends JpaRepository<Correntista, Integer> {

}
