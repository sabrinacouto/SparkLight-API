package com.fiap.sparklight_api.services;

import com.fiap.sparklight_api.dto.AparelhoDTO;
import com.fiap.sparklight_api.exceptions.AparelhoNotFoundException;
import com.fiap.sparklight_api.exceptions.DatabaseException;
import com.fiap.sparklight_api.mapper.AparelhoMapper;
import com.fiap.sparklight_api.model.Aparelho;
import com.fiap.sparklight_api.repository.AparelhoRepository;
import com.fiap.sparklight_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AparelhoService {

    @Autowired
    private AparelhoRepository aparelhoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AparelhoMapper aparelhoMapper;

    public Page<AparelhoDTO> getAllAparelhos(Pageable pageable) {
        Page<Aparelho> aparelhos = aparelhoRepository.findAll(pageable);
        return aparelhos.map(aparelhoMapper::toDTO);
    }

    public AparelhoDTO getAparelhoById(Long id) {
        Aparelho aparelho = aparelhoRepository.findById(id)
                .orElseThrow(() -> new AparelhoNotFoundException(id));
        return aparelhoMapper.toDTO(aparelho);
    }

    public AparelhoDTO createAparelho(AparelhoDTO dto) {
        if (!usuarioRepository.existsById(dto.getUsuarioId())) {
            throw new AparelhoNotFoundException("Usuário não encontrado.");
        }

        Aparelho aparelho = aparelhoMapper.toEntity(dto);
        try {
            Aparelho savedAparelho = aparelhoRepository.save(aparelho);
            return aparelhoMapper.toDTO(savedAparelho);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao criar o aparelho: " + e.getMessage());
        }
    }

    public AparelhoDTO updateAparelho(Long id, AparelhoDTO dto) {
        Aparelho aparelho = aparelhoRepository.findById(id)
                .orElseThrow(() -> new AparelhoNotFoundException(id));

        aparelho.setNome(dto.getNome());
        aparelho.setPotencia(dto.getPotencia());
        aparelho.setTempo(dto.getTempo());
        aparelho.setPeriodo(dto.getPeriodo());

        try {
            Aparelho updatedAparelho = aparelhoRepository.save(aparelho);
            return aparelhoMapper.toDTO(updatedAparelho);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao atualizar o aparelho: " + e.getMessage());
        }
    }

    public void deleteAparelho(Long id) {
        Aparelho aparelho = aparelhoRepository.findById(id)
                .orElseThrow(() -> new AparelhoNotFoundException(id));

        try {
            aparelhoRepository.delete(aparelho);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao excluir o aparelho: " + e.getMessage());
        }
    }
}

