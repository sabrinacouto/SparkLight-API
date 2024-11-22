package com.fiap.sparklight_api.mapper;

import com.fiap.sparklight_api.dto.UsuarioDTO;
import com.fiap.sparklight_api.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setUsuarioId(usuario.getUsuarioId());
        dto.setNome(usuario.getNome());
        dto.setSobrenome(usuario.getSobrenome());
        dto.setEmail(usuario.getEmail());
        dto.setCidade(usuario.getCidade());
        dto.setEstado(usuario.getEstado());
        dto.setIdade(usuario.getIdade());
        dto.setGenero(usuario.getGenero());
        dto.setSenha(usuario.getSenha());
        dto.setCep(usuario.getCep());
        return dto;
    }

    public Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setSobrenome(dto.getSobrenome());
        usuario.setEmail(dto.getEmail());
        usuario.setCidade(dto.getCidade());
        usuario.setEstado(dto.getEstado());
        usuario.setIdade(dto.getIdade());
        usuario.setGenero(dto.getGenero());
        usuario.setSenha(dto.getSenha());
        usuario.setCep(dto.getCep());
        return usuario;
    }
}



