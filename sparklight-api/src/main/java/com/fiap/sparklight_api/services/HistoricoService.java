package com.fiap.sparklight_api.services;

import com.fiap.sparklight_api.dto.HistoricoDTO;
import com.fiap.sparklight_api.exceptions.HistoricoNotFoundException;
import com.fiap.sparklight_api.mapper.HistoricoMapper;
import com.fiap.sparklight_api.model.Historico;
import com.fiap.sparklight_api.model.Usuario;
import com.fiap.sparklight_api.repository.HistoricoRepository;
import com.fiap.sparklight_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HistoricoMapper historicoMapper;

    public Page<HistoricoDTO> getAllHistoricos(Pageable pageable) {
        Page<Historico> historicos = historicoRepository.findAll(pageable);
        return historicos.map(historicoMapper::toDTO);
    }

    public HistoricoDTO getHistoricoById(Long id) {
        Historico historico = historicoRepository.findById(id)
                .orElseThrow(() -> new HistoricoNotFoundException(id));
        return historicoMapper.toDTO(historico);
    }

    public HistoricoDTO createHistorico(@Valid HistoricoDTO dto) {
        if (!usuarioRepository.existsById(dto.getUsuarioId())) {
            throw new HistoricoNotFoundException("Usuário não encontrado.");
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new HistoricoNotFoundException("Usuário não encontrado."));

        Historico historico = historicoMapper.toEntity(dto, usuario);


        Historico savedHistorico = historicoRepository.save(historico);

        return historicoMapper.toDTO(savedHistorico);
    }


    public HistoricoDTO updateHistorico(Long id, @Valid HistoricoDTO dto) {
        Historico historico = historicoRepository.findById(id)
                .orElseThrow(() -> new HistoricoNotFoundException(id));

        historico.setMes(dto.getMes());
        historico.setAno(dto.getAno());
        historico.setConsumoMes(dto.getConsumoMes());
        historico.setCustoMes(dto.getCustoMes());

        try {
            Historico updatedHistorico = historicoRepository.save(historico);
            return historicoMapper.toDTO(updatedHistorico);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o histórico: " + e.getMessage());
        }
    }

    public void deleteHistorico(Long id) {
        Historico historico = historicoRepository.findById(id)
                .orElseThrow(() -> new HistoricoNotFoundException(id));
        historicoRepository.delete(historico);
    }
}
