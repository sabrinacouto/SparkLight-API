package com.fiap.sparklight_api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fiap.sparklight_api.utils.BigDecimalFormatter;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
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

    @JsonSerialize(using = BigDecimalFormatter.class)
    private BigDecimal consumoMes;

    @JsonSerialize(using = BigDecimalFormatter.class)
    private BigDecimal custoMensal;

    @NotNull(message = "O aparelho é obrigatório.")
    private Long aparelhoId;

    @NotNull(message = "O histórico é obrigatório.")
    private Long historicoId;

}

