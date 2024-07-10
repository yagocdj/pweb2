package br.edu.ifpb.pweb2.bitbank.repository;

import br.edu.ifpb.pweb2.bitbank.model.Conta;
import br.edu.ifpb.pweb2.bitbank.model.Correntista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {
    Conta findByCorrentista(Correntista correntista);

    @Query("FROM Conta c LEFT JOIN FETCH c.transacoes t WHERE c.numero = :numero")
    Optional<Conta> findByNumeroWithTransacoes(@Param("numero") String numeroConta);

    @Query("SELECT DISTINCT c FROM Conta c LEFT JOIN FETCH c.transacoes t WHERE c.id = :id")
    Optional<Conta> findByIdWithTransacoes(@Param("id") Integer idDaConta);
}
