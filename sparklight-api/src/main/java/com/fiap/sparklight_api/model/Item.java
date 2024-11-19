package com.fiap.sparklight_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_historico_id")
    @SequenceGenerator(name = "seq_historico_id", sequenceName = "seq_historico_id", allocationSize = 1)
    private Long itemId;

    @NotNull(message = "A quantidade é obrigatória.")
    @Positive(message = "A quantidade deve ser positiva.")
    private Integer quantidade;

    @PositiveOrZero(message = "O consumo mensal não pode ser negativo.")
    private BigDecimal consumomes;

    @PositiveOrZero(message = "O custo mensal não pode ser negativo.")
    private BigDecimal customensal;

    @NotNull(message = "O aparelho é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "tb_aparelho_aparelho_id")
    private Aparelho aparelho;

    @NotNull(message = "O histórico é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "tb_historico_historico_id")
    private Historico historico;
}

