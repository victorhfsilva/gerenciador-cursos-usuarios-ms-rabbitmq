package com.example.usuariosms.fixture;

import com.example.usuariosms.model.Endereco;
import com.example.usuariosms.model.dto.RegistrarEnderecoRequest;
import com.example.usuariosms.model.enums.Estado;

public interface EnderecoFixture {

    static Endereco buildValido() {
        return builder().build();
    }

    static Endereco.EnderecoBuilder builder() {
        return Endereco.builder()
                .id(1L)
                .cep("12345678")
                .logradouro("Rua dos Bobos")
                .numero("12")
                .complemento("Casa")
                .bairro("Jardim das Flores")
                .cidade("São Paulo")
                .estado(Estado.AC);
    }

}
