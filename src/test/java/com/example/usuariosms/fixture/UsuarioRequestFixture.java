package com.example.usuariosms.fixture;

import com.example.usuariosms.model.dto.UsuarioRequest;
import com.example.usuariosms.model.enums.Papel;

import java.time.LocalDate;

public interface UsuarioRequestFixture {

    static UsuarioRequest buildValido() {
        return builder().build();
    }

    private static UsuarioRequest.UsuarioRequestBuilder builder() {
        return UsuarioRequest.builder()
                .nome("João")
                .sobrenome("Bezerra da Silva")
                .cpf("78664841209")
                .senha("Senha@123")
                .papel(Papel.ADMIN)
                .dataNascimento(LocalDate.of(2000, 6, 6))
                .email("joaobsilva@email.com")
                .celular("11999998888")
                .endereco(EnderecoRequestFixture.buildValido());
    }

}
