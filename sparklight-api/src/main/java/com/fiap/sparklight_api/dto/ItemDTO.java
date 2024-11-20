package com.fiap.sparklight_api.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper=false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO extends RepresentationModel<ItemDTO> {

    private Long itemId;

    @NotNull(message = "A quantidade é obrigatória.")
    @Positive(message = "A quantidade deve ser positiva.")
    private Integer quantidade;

    private BigDecimal consumoMes;

    private BigDecimal custoMensal;

    @NotNull(message = "O aparelho é obrigatório.")
    private Long aparelhoId;

    @NotNull(message = "O histórico é obrigatório.")
    private Long historicoId;

}

