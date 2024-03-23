package br.edu.ifpb.pweb2.bitbank.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String numero;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    private Set<Transacao> transacoes = new HashSet<Transacao>();

    private Correntista correntista = new Correntista();

    public BigDecimal getSaldo() {
        BigDecimal total = BigDecimal.ZERO;
        for (Transacao t : this.transacoes) {
            total = total.add(t.getValor());
        }
        return total;
    }
}