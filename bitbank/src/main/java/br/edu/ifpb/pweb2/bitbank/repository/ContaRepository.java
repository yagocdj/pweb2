package br.edu.ifpb.pweb2.bitbank.repository;

import br.edu.ifpb.pweb2.bitbank.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    Optional<Conta> findByNumero(String numeroConta);

    Optional<Conta> findById(Integer idDaConta);
}
