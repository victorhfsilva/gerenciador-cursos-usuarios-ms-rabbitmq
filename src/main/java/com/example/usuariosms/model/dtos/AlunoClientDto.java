package com.example.usuariosms.model.dtos;

import lombok.Builder;

import java.util.UUID;

@Builder
public record AlunoClientDto(
        UUID usuarioId
){

}