package com.example.usuariosms.fixture;

import com.example.usuariosms.model.requests.EnderecoRequest;
import com.example.usuariosms.model.enums.Estado;

public interface EnderecoRequestFixture {

    static EnderecoRequest buildValido() {
        return builder().build();
    }

    private static EnderecoRequest.EnderecoRequestBuilder builder() {
        return EnderecoRequest.builder()
                .cep("12345678")
                .logradouro("Rua dos Bobos")
                .numero("12")
                .complemento("Casa")
                .bairro("Jardim das Flores")
                .cidade("São Paulo")
                .estado(Estado.AC);
    }
}
