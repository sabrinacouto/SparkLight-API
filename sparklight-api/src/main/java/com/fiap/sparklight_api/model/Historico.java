package com.fiap.sparklight_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

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

    @NotNull(message = "O mês é obrigatório.")
    @Min(value = 1, message = "O mês deve ser no mínimo 1.")
    @Max(value = 12, message = "O mês deve ser no máximo 12.")
    private Integer mes;

    @NotNull(message = "O ano é obrigatório.")
    @Min(value = 1900, message = "O ano deve ser no mínimo 1900.")
    @Max(value = 2100, message = "O ano deve ser no máximo 2100.")
    private Integer ano;

    @NotNull(message = "O consumo do mês é obrigatório.")
    @PositiveOrZero(message = "O consumo não pode ser negativo.")
    private Double consumomes;

    @NotNull(message = "O custo do mês é obrigatório.")
    @PositiveOrZero(message = "O custo não pode ser negativo.")
    private Double customes;

    @NotNull(message = "O usuário é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "tb_usuario_usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "historico", cascade = CascadeType.ALL)
    private List<Item> itens;
}

