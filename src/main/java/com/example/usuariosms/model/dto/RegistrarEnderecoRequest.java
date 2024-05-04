package com.example.usuariosms.model.dto;

import com.example.usuariosms.model.enums.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegistrarEnderecoRequest (
    @NotBlank
    @Size(max = 8)
    String cep,

    @NotBlank
    @Size(max = 10)
    String numero,

    @Size(max = 100)
    String complemento,

    @NotBlank
    @Size(max = 100)
    String logradouro,

    @NotBlank
    @Size(max = 100)
    String bairro,

    @NotBlank
    @Size(max = 100)
    String cidade,

    @NotNull
    Estado estado

){

}

