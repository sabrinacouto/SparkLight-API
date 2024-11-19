package com.fiap.sparklight_api.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoDTO extends RepresentationModel<HistoricoDTO> {

    private Long historicoId;

    @NotNull(message = "O mês é obrigatório.")
    @Min(value = 1, message = "O mês deve ser no mínimo 1.")
    @Max(value = 12, message = "O mês deve ser no máximo 12.")
    private Integer mes;

    @NotNull(message = "O ano é obrigatório.")
    @Min(value = 1900, message = "O ano deve ser no mínimo 1900.")
    @Max(value = 2100, message = "O ano deve ser no máximo 2100.")
    private Integer ano;

    private Double consumomes;

    private Double customes;

    @NotNull(message = "O usuário é obrigatório.")
    private Long usuarioId;

    private List<ItemDTO> itens;
}

