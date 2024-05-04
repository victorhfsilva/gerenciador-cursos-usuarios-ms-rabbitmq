package com.example.usuariosms.fixture;

import com.example.usuariosms.model.dto.RegistrarEnderecoRequest;
import com.example.usuariosms.model.enums.Estado;

public interface RegistrarEnderecoRequestFixture {

    static RegistrarEnderecoRequest buildValido() {
        return builder().build();
    }

    static RegistrarEnderecoRequest.RegistrarEnderecoRequestBuilder builder() {
        return RegistrarEnderecoRequest.builder()
                .cep("12345678")
                .logradouro("Rua dos Bobos")
                .numero("12")
                .complemento("Casa")
                .bairro("Jardim das Flores")
                .cidade("São Paulo")
                .estado(Estado.AC);
    }
}
