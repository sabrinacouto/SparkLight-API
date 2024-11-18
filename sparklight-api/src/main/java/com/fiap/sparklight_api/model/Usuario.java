package com.fiap.sparklight_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario_id")
    @SequenceGenerator(name = "seq_usuario_id", sequenceName = "seq_usuario_id", allocationSize = 1)
    private Long usuarioId;

    @Column(nullable = false)
    private String nome;

    @Column(length = 55)
    private String sobrenome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 12)
    private String cep;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false)
    private String senha;
}


