package com.fiap.sparklight_api.services;

import com.fiap.sparklight_api.dto.HistoricoDTO;
import com.fiap.sparklight_api.exceptions.HistoricoNotFoundException;
import com.fiap.sparklight_api.exceptions.InvalidDataException;
import com.fiap.sparklight_api.mapper.HistoricoMapper;
import com.fiap.sparklight_api.model.Aparelho;
import com.fiap.sparklight_api.model.Historico;
import com.fiap.sparklight_api.model.Usuario;
import com.fiap.sparklight_api.repository.HistoricoRepository;
import com.fiap.sparklight_api.repository.UsuarioRepository;
import com.fiap.sparklight_api.repository.AparelhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class HistoricoService {

    @Autowired
    private AparelhoRepository aparelhoRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HistoricoMapper historicoMapper;

    public HistoricoDTO calcularConsumoECusto(Long usuarioId, Pageable pageable) {
        Page<Aparelho> aparelhosPage = aparelhoRepository.findByUsuario_UsuarioId(usuarioId, pageable);

        BigDecimal consumoMes = BigDecimal.ZERO;
        BigDecimal custoMes = BigDecimal.ZERO;


        BigDecimal tarifaKWh = new BigDecimal(0.50);

        List<Aparelho> aparelhos = aparelhosPage.getContent();
        for (Aparelho aparelho : aparelhos) {
            consumoMes = consumoMes.add(aparelho.calcularConsumoMensal());
            custoMes = custoMes.add(aparelho.calcularCustoMensal(tarifaKWh));
        }


        HistoricoDTO historicoDTO = new HistoricoDTO();
        historicoDTO.setConsumoMes(consumoMes);
        historicoDTO.setCustoMes(custoMes);
        historicoDTO.setUsuarioId(usuarioId);

        return historicoDTO;
    }


    public Page<HistoricoDTO> getAllHistoricos(Pageable pageable) {
        Page<Historico> historicos = historicoRepository.findAll(pageable);
        return historicos.map(historicoMapper::toDTO);
    }


    public HistoricoDTO getHistoricoById(Long id) {
        Historico historico = historicoRepository.findById(id)
                .orElseThrow(() -> new HistoricoNotFoundException(id));
        return historicoMapper.toDTO(historico);
    }


    public HistoricoDTO createHistorico(HistoricoDTO dto) {

        if (dto.getMes() == null || dto.getAno() == null) {
            throw new InvalidDataException("Mês e ano são obrigatórios.");
        }

        if (dto.getUsuarioId() == null) {
            throw new InvalidDataException("Usuário é obrigatório.");
        }


        usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Historico historico = historicoMapper.toEntity(dto, usuario);
        System.out.println("Salvando o histórico: " + historico);


        Historico savedHistorico = historicoRepository.save(historico);


        return historicoMapper.toDTO(savedHistorico);
    }


    public HistoricoDTO updateHistorico(Long id, HistoricoDTO dto) {
        Historico historico = historicoRepository.findById(id)
                .orElseThrow(() -> new HistoricoNotFoundException(id));

        if (dto.getMes() == null || dto.getAno() == null) {
            throw new InvalidDataException("Mês e ano são obrigatórios.");
        }


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


