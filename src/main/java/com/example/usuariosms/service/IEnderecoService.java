package com.example.usuariosms.service;

import com.example.usuariosms.model.requests.EnderecoRequest;
import com.example.usuariosms.model.resources.EnderecoResource;

import java.util.UUID;

public interface IEnderecoService {
    EnderecoResource findByUsuarioId(UUID usuarioId);
    EnderecoResource update(UUID usuarioId, EnderecoRequest enderecoDto);
}
