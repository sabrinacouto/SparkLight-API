package com.fiap.sparklight_api.controller;

import com.fiap.sparklight_api.dto.UsuarioDTO;
import com.fiap.sparklight_api.services.UsuarioService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO savedUsuario = usuarioService.createUser(usuarioDTO);

        var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .getUsuarioById(savedUsuario.getUsuarioId())).withSelfRel();
        var allUsuariosLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .getAllUsuarios(Pageable.unpaged())).withRel("usuarios");

        savedUsuario.add(selfLink, allUsuariosLink);

        return ResponseEntity.created(selfLink.toUri()).body(savedUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = usuarioService.getUserById(id);

        if (usuarioDTO == null) {
            return ResponseEntity.notFound().build();
        }

        var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .getUsuarioById(id)).withSelfRel();
        var allUsuariosLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .getAllUsuarios(Pageable.unpaged())).withRel("usuarios");

        usuarioDTO.add(selfLink, allUsuariosLink);

        return ResponseEntity.ok(usuarioDTO);
    }

    // Update Usuario
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id,
                                                    @Valid @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO updatedUsuario = usuarioService.updateUser(id, usuarioDTO);

        if (updatedUsuario == null) {
            return ResponseEntity.notFound().build();
        }

        var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .getUsuarioById(updatedUsuario.getUsuarioId())).withSelfRel();
        var allUsuariosLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .getAllUsuarios(Pageable.unpaged())).withRel("usuarios");

        updatedUsuario.add(selfLink, allUsuariosLink);

        return ResponseEntity.ok(updatedUsuario);
    }

    // Delete Usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<UsuarioDTO>>> getAllUsuarios(Pageable pageable) {
        Page<UsuarioDTO> usuariosDTO = usuarioService.getAllUsers(pageable);

        var usuarioModels = usuariosDTO.map(usuarioDTO -> {
            var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                    .getUsuarioById(usuarioDTO.getUsuarioId())).withSelfRel();
            var allUsuariosLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                    .getAllUsuarios(pageable)).withRel("usuarios");

            usuarioDTO.add(selfLink, allUsuariosLink);

            return EntityModel.of(usuarioDTO);
        });

        return ResponseEntity.ok(CollectionModel.of(usuarioModels));
    }
}

