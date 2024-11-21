package com.fiap.sparklight_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Table(name = "tb_historico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_historico_id")
    @SequenceGenerator(name = "seq_historico_id", sequenceName = "seq_historico_id", allocationSize = 1)
    @Column(name = "historico_id")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tb_usuario_usuario_id")
    private Usuario usuario;


}
