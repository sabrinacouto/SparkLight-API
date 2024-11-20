package com.fiap.sparklight_api.mapper;

import com.fiap.sparklight_api.dto.HistoricoDTO;
import com.fiap.sparklight_api.model.Historico;
import com.fiap.sparklight_api.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class HistoricoMapper {

    public HistoricoDTO toDTO(Historico historico) {
        HistoricoDTO dto = new HistoricoDTO();
        dto.setHistoricoId(historico.getHistoricoId());
        dto.setMes(historico.getMes());
        dto.setAno(historico.getAno());
        dto.setConsumomes(historico.getConsumomes());
        dto.setCustomes(historico.getCustomes());
        dto.setUsuarioId(historico.getUsuario().getUsuarioId());
        return dto;
    }

    public Historico toEntity(HistoricoDTO dto, Usuario usuario) {
        Historico historico = new Historico();
        historico.setHistoricoId(dto.getHistoricoId());
        historico.setMes(dto.getMes());
        historico.setAno(dto.getAno());
        historico.setConsumomes(dto.getConsumomes());
        historico.setCustomes(dto.getCustomes());
        historico.setUsuario(usuario);
        return historico;
    }
}
