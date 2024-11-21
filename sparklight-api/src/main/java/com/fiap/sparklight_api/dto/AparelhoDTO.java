package com.fiap.sparklight_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fiap.sparklight_api.utils.BigDecimalFormatter;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper=false)
@Data
public class AparelhoDTO extends RepresentationModel<AparelhoDTO> {

    private Long aparelhoId;

    @NotBlank(message = "O nome do aparelho não pode ser vazio.")
    private String nome;

    @PositiveOrZero(message = "A potência não pode ser negativa.")
    private BigDecimal potencia;

    @PositiveOrZero(message = "O tempo não pode ser negativo.")
    private BigDecimal tempo;

    @NotBlank(message = "O período não pode ser vazio.")
    private String periodo;

    @NotNull(message = "O usuário é obrigatório.")
    private Long usuarioId;

    @JsonSerialize(using = BigDecimalFormatter.class)
    private BigDecimal consumoMensal;

    @JsonSerialize(using = BigDecimalFormatter.class)
    private BigDecimal custoMensal;
}

