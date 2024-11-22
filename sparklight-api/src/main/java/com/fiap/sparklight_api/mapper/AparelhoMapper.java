package com.fiap.sparklight_api.mapper;

import com.fiap.sparklight_api.dto.AparelhoDTO;
import com.fiap.sparklight_api.model.Aparelho;
import com.fiap.sparklight_api.model.Usuario;
import com.fiap.sparklight_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AparelhoMapper {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public AparelhoDTO toDTO(Aparelho aparelho) {
        AparelhoDTO dto = new AparelhoDTO();
        dto.setAparelhoId(aparelho.getAparelhoId());
        dto.setNome(aparelho.getNome());
        dto.setPotencia(aparelho.getPotencia());
        dto.setTempo(aparelho.getTempo());
        dto.setPeriodo(aparelho.getPeriodo());
        dto.setUsuarioId(aparelho.getUsuario().getUsuarioId());

        dto.setConsumoMensal(aparelho.calcularConsumoMensal());
        dto.setCustoMensal(aparelho.calcularCustoMensal(BigDecimal.valueOf(0.50)));

        return dto;
    }

    public Aparelho toEntity(AparelhoDTO dto) {
        if (dto.getUsuarioId() == null) {
            throw new IllegalArgumentException("O campo 'usuarioId' é obrigatório");
        }

        Aparelho aparelho = new Aparelho();
        aparelho.setAparelhoId(dto.getAparelhoId());
        aparelho.setNome(dto.getNome());
        aparelho.setPotencia(dto.getPotencia());
        aparelho.setTempo(dto.getTempo());
        aparelho.setPeriodo(dto.getPeriodo());

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        aparelho.setUsuario(usuario);
        return aparelho;
    }
}

