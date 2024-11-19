package com.fiap.sparklight_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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

    @Column(nullable = false)
    private Integer quantidade;

    @Column(precision = 10, scale = 2)
    private Double consumoMensal;

    @Column(precision = 10, scale = 2)
    private Double custoMensal;

    @ManyToOne
    @JoinColumn(name = "tb_aparelho_aparelho_id", nullable = false)
    private Aparelho aparelho;

    @ManyToOne
    @JoinColumn(name = "tb_historico_historico_id", nullable = false)
    private Historico historico;
}
