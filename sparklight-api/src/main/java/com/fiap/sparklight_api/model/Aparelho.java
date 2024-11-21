package com.fiap.sparklight_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
    private BigDecimal potencia;

    @Column(precision = 10, scale = 2)
    private BigDecimal tempo;

    @Column(length = 45)
    private String periodo;

    @ManyToOne
    @JoinColumn(name = "tb_usuario_usuario_id", nullable = false)
    private Usuario usuario;



    public BigDecimal calcularConsumoMensal() {

        BigDecimal potenciaKW = this.potencia.divide(new BigDecimal(1000), 2, RoundingMode.HALF_UP);


        BigDecimal consumoMensal = potenciaKW.multiply(this.tempo).multiply(new BigDecimal(30));

        return consumoMensal;
    }

    public BigDecimal calcularCustoMensal(BigDecimal valorKWh) {

        BigDecimal consumoMensal = calcularConsumoMensal();
        return consumoMensal.multiply(valorKWh);
    }
}
