package br.edu.ifpb.pweb2.bitbank.repository;

import br.edu.ifpb.pweb2.bitbank.model.Conta;
import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    public Optional<Conta> findByNumeroWithTransacoes(String numeroConta);

    public Optional<Conta> findByIdWithTransacoes(Integer idDaConta);
}
