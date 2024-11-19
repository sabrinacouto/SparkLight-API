package com.fiap.sparklight_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper=false)
@Data
public class AparelhoDTO extends RepresentationModel<AparelhoDTO> {

    private Long aparelhoId;

    @NotBlank(message = "O nome do aparelho não pode ser vazio.")
    private String nome;

    @PositiveOrZero(message = "A potência não pode ser negativa.")
    private Double potencia;

    @PositiveOrZero(message = "O tempo não pode ser negativo.")
    private Double tempo;

    @NotBlank(message = "O período não pode ser vazio.")
    private String periodo;

    @NotNull(message = "O usuário é obrigatório.")
    private Long usuarioId;
}

