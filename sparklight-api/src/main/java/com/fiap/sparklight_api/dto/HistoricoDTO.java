package com.fiap.sparklight_api.dto;

import com.fiap.sparklight_api.model.Historico;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper=false)
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

    private BigDecimal consumoMes;

    private BigDecimal custoMes;

    @NotNull(message = "O usuário é obrigatório.")
    private Long usuarioId;

    public HistoricoDTO(Historico historico) {
        this.historicoId = historico.getHistoricoId();
        this.ano = historico.getAno();
        this.consumoMes = historico.getConsumoMes();
        this.custoMes = historico.getCustoMes();
        this.mes = historico.getMes();
        this.usuarioId = historico.getUsuario().getUsuarioId(); // Ajuste conforme necessário
    }

}

