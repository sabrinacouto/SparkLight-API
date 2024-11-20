package com.fiap.sparklight_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item_id")
    @SequenceGenerator(name = "seq_item_id", sequenceName = "seq_item_id", allocationSize = 1)
    private Long itemId;

    @Column(nullable = false)
    private Integer quantidade;

    @Column
    private BigDecimal consumoMes;

    @Column
    private BigDecimal custoMensal;

    @ManyToOne
    @JoinColumn(name = "tb_aparelho_aparelho_id", nullable = false)
    private Aparelho aparelho;

    @ManyToOne
    @JoinColumn(name = "tb_historico_historico_id", nullable = false)
    private Historico historico;

    public void calcularValores(BigDecimal valorKWh) {
        if (aparelho != null) {

            // Cálculo de consumo mensal individual do aparelho
            BigDecimal consumoIndividual = aparelho.calcularConsumoMensal();

            // Cálculo de custo mensal individual do aparelho
            BigDecimal custoIndividual = consumoIndividual.multiply(valorKWh);

            // Cálculo do consumo total considerando a quantidade de aparelhos
            this.consumoMes = consumoIndividual.multiply(BigDecimal.valueOf(quantidade));

            // Cálculo do custo total considerando a quantidade de aparelhos
            this.custoMensal = custoIndividual.multiply(BigDecimal.valueOf(quantidade));
        }
    }
}


