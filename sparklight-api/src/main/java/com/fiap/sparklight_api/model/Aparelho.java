package com.fiap.sparklight_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_aparelho")
public class Aparelho {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_aparelho_id")
    @SequenceGenerator(name = "seq_aparelho_id", sequenceName = "seq_aparelho_id", allocationSize = 1)
    private Long aparelhoId;

    @Column(length = 55)
    private String nome;

    @Column(precision = 10, scale = 2)
    private Double potencia;

    @Column(precision = 10, scale = 2)
    private Double tempo;

    @Column(length = 45)
    private String periodo;

    @ManyToOne
    @JoinColumn(name = "tb_usuario_usuario_id", nullable = false)
    private Usuario usuario;
}
