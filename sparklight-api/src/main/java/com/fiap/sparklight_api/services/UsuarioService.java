package com.fiap.sparklight_api.services;

import com.fiap.sparklight_api.dto.UsuarioDTO;
import com.fiap.sparklight_api.exceptions.EmailAlreadyExistsException;
import com.fiap.sparklight_api.mapper.UsuarioMapper;
import com.fiap.sparklight_api.model.Usuario;
import com.fiap.sparklight_api.repository.UsuarioRepository;
import com.fiap.sparklight_api.exceptions.UserNotFoundException;
import com.fiap.sparklight_api.exceptions.InvalidDataException;
import com.fiap.sparklight_api.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public Page<UsuarioDTO> getAllUsers(Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        return usuarios.map(usuarioMapper::toDTO);
    }

    public UsuarioDTO getUserById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return usuarioMapper.toDTO(usuario);
    }

    public UsuarioDTO createUser(UsuarioDTO dto) {
        if (dto.getNome() == null || dto.getEmail() == null) {
            throw new InvalidDataException("Nome e email são obrigatórios.");
        }

        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Já existe um usuário com este e-mail.");
        }

        Usuario usuario = usuarioMapper.toEntity(dto);
        Usuario savedUser = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(savedUser);
    }


    public UsuarioDTO updateUser(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        usuario.setNome(dto.getNome());
        usuario.setSobrenome(dto.getSobrenome());
        usuario.setEmail(dto.getEmail());
        usuario.setCep(dto.getCep());
        usuario.setCidade(dto.getCidade());
        usuario.setEstado(dto.getEstado());
        usuario.setGenero(dto.getGenero());
        usuario.setIdade(dto.getIdade());
        usuario.setSenha(dto.getSenha());

        try {
            Usuario updatedUser = usuarioRepository.save(usuario);
            return usuarioMapper.toDTO(updatedUser);
        } catch (Exception e) {
            throw new DatabaseException("Erro ao atualizar o usuário: " + e.getMessage());
        }
    }

    public void deleteUser(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        usuarioRepository.delete(usuario);
    }
}


