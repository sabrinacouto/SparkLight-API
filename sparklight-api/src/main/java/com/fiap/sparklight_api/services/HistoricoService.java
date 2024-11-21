package com.fiap.sparklight_api.services;

import com.fiap.sparklight_api.dto.HistoricoDTO;
import com.fiap.sparklight_api.exceptions.HistoricoNotFoundException;
import com.fiap.sparklight_api.mapper.HistoricoMapper;
import com.fiap.sparklight_api.model.Aparelho;
import com.fiap.sparklight_api.model.Historico;
import com.fiap.sparklight_api.model.Usuario;
import com.fiap.sparklight_api.repository.AparelhoRepository;
import com.fiap.sparklight_api.repository.HistoricoRepository;
import com.fiap.sparklight_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

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

    // Método para obter todos os históricos com paginação
    public Page<HistoricoDTO> getAllHistoricos(Pageable pageable) {
        Page<Historico> historicos = historicoRepository.findAll(pageable);
        return historicos.map(historicoMapper::toDTO);
    }

    // Método para calcular o consumo e custo mensal para um usuário
    public HistoricoDTO calcularConsumoECusto(Long usuarioId, Pageable pageable) {
        // Corrigindo a consulta para utilizar findByUsuario_Id
        Page<Aparelho> aparelhosPage = aparelhoRepository.findByUsuario_UsuarioId(usuarioId, pageable);

        BigDecimal consumoMes = BigDecimal.ZERO;
        BigDecimal custoMes = BigDecimal.ZERO;

        // Definindo o valor da tarifa
        BigDecimal tarifaKWh = new BigDecimal(0.50); // Tarifa hipotética

        List<Aparelho> aparelhos = aparelhosPage.getContent();
        for (Aparelho aparelho : aparelhos) {
            consumoMes = consumoMes.add(aparelho.calcularConsumoMensal());
            custoMes = custoMes.add(aparelho.calcularCustoMensal(tarifaKWh));
        }

        // Prepara o DTO com os valores calculados
        HistoricoDTO historicoDTO = new HistoricoDTO();
        historicoDTO.setConsumoMes(consumoMes);
        historicoDTO.setCustoMes(custoMes);
        historicoDTO.setUsuarioId(usuarioId);

        return historicoDTO;
    }

    // Método para obter um histórico específico por ID
    public HistoricoDTO getHistoricoById(Long id) {
        Historico historico = historicoRepository.findById(id)
                .orElseThrow(() -> new HistoricoNotFoundException(id));
        return historicoMapper.toDTO(historico);
    }

    public HistoricoDTO createHistorico(HistoricoDTO dto) {
        // Verifique se o dto tem todos os dados necessários
        Historico historico = new Historico();
        historico.setAno(dto.getAno());
        historico.setConsumoMes(dto.getConsumoMes());
        historico.setCustoMes(dto.getCustoMes());
        historico.setMes(dto.getMes());
        historico.setUsuario(usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")));

        // Persistindo o historico
        Historico savedHistorico = historicoRepository.save(historico);

        // Retornando o DTO
        return new HistoricoDTO(savedHistorico);
    }

    // Método para atualizar um histórico existente
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

    // Método para excluir um histórico
    public void deleteHistorico(Long id) {
        Historico historico = historicoRepository.findById(id)
                .orElseThrow(() -> new HistoricoNotFoundException(id));
        historicoRepository.delete(historico);
    }
}

