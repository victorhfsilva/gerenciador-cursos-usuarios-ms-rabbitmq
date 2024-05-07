package com.example.usuariosms.fixture;

import com.example.usuariosms.model.dto.EnderecoRequest;
import com.example.usuariosms.model.enums.Estado;
import com.example.usuariosms.model.resources.EnderecoResource;

public interface EnderecoResourceFixture {
    static EnderecoResource buildValido() {
        return builder().build();
    }

    private static EnderecoResource.EnderecoResourceBuilder builder() {
        return EnderecoResource.builder()
                .cep("12345678")
                .logradouro("Rua dos Bobos")
                .numero("12")
                .complemento("Casa")
                .bairro("Jardim das Flores")
                .cidade("São Paulo")
                .estado(Estado.AC);
    }
}
