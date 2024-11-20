package com.fiap.sparklight_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tb_historico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_historico_id")
    @SequenceGenerator(name = "seq_historico_id", sequenceName = "seq_historico_id", allocationSize = 1)
    private Long historicoId;

    @Column(nullable = false)
    private Integer mes;

    @Column(nullable = false)
    private Integer ano;

    @Column(name = "consumomes", nullable = false)
    private BigDecimal consumoMes;

    @Column(name = "customes", nullable = false)
    private BigDecimal custoMes;

    @NotNull(message = "O usuário é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "tb_usuario_usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "historico", cascade = CascadeType.ALL)
    private List<Item> itens;

    public void calcularTotais(BigDecimal valorKWh) {
        this.consumoMes = BigDecimal.ZERO;
        this.custoMes = BigDecimal.ZERO;

        for (Item item : itens) {
            item.calcularValores(valorKWh);
            this.consumoMes = this.consumoMes.add(item.getConsumoMes());
            this.custoMes = this.custoMes.add(item.getCustoMensal());
        }
    }
}
