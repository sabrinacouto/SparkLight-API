package com.fiap.sparklight_api.mapper;

import com.fiap.sparklight_api.dto.HistoricoDTO;
import com.fiap.sparklight_api.model.Historico;
import com.fiap.sparklight_api.model.Usuario;
import org.springframework.stereotype.Component;


@Component
public class HistoricoMapper {

    public HistoricoDTO toDTO(Historico historico) {
        HistoricoDTO dto = new HistoricoDTO();
        dto.setHistoricoId(historico.getHistoricoId());
        dto.setMes(historico.getMes());
        dto.setAno(historico.getAno());
        dto.setConsumoMes(historico.getConsumoMes());
        dto.setCustoMes(historico.getCustoMes());
        dto.setUsuarioId(historico.getUsuario().getUsuarioId());
        return dto;
    }

    public Historico toEntity(HistoricoDTO dto, Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo ao criar histórico.");
        }

        Historico historico = new Historico();
        historico.setHistoricoId(dto.getHistoricoId());
        historico.setMes(dto.getMes());
        historico.setAno(dto.getAno());
        historico.setConsumoMes(dto.getConsumoMes());
        historico.setCustoMes(dto.getCustoMes());
        historico.setUsuario(usuario);
        return historico;
    }
}


