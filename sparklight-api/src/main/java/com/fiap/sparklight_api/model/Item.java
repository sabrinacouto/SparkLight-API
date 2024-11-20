package com.fiap.sparklight_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item_id")
    @SequenceGenerator(name = "seq_item_id", sequenceName = "seq_item_id", allocationSize = 1)
    private Long itemId;

    @Column
    private Integer quantidade;

    @Column
    private BigDecimal consumomes;

    @Column
    private BigDecimal customensal;

    @ManyToOne
    @JoinColumn(name = "tb_aparelho_aparelho_id", nullable = false)
    private Aparelho aparelho;

    @ManyToOne
    @JoinColumn(name = "tb_historico_historico_id", nullable = false)
    private Historico historico;

    public void calcularConsumoECusto() {
        if (aparelho != null) {

            BigDecimal consumo = aparelho.calcularConsumoMensal();
            BigDecimal custo = aparelho.calcularCustoMensal(BigDecimal.valueOf(0.60));

            this.consumomes = consumo.multiply(BigDecimal.valueOf(quantidade));
            this.customensal = custo.multiply(BigDecimal.valueOf(quantidade));
        }
    }
}


