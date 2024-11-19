package com.fiap.sparklight_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper=false)
@Data
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {

    private Long usuarioId;

    @NotBlank(message = "O nome não pode ser vazio.")
    private String nome;

    @NotBlank(message = "O sobrenome não pode ser vazio.")
    private String sobrenome;

    @Email(message = "O email deve ser válido.")
    @NotBlank(message = "O email não pode ser vazio.")
    private String email;

    @NotNull(message = "O CEP é obrigatório.")
    private String cep;

    @NotBlank(message = "A cidade não pode ser vazia.")
    private String cidade;

    @NotBlank(message = "O estado não pode ser vazio.")
    private String estado;

    @NotNull(message = "A idade não pode ser nula.")
    private Integer idade;

    @NotBlank(message = "O gênero não pode ser vazio.")
    private String genero;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;
}


