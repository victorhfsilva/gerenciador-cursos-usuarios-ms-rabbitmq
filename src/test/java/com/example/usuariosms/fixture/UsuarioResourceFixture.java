package com.example.usuariosms.fixture;

import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.enums.Papel;
import com.example.usuariosms.model.resources.UsuarioResource;

import java.time.LocalDate;
import java.util.UUID;

public interface UsuarioResourceFixture {
    static UsuarioResource buildValido() {
        return builder().build();
    }
    private static UsuarioResource.UsuarioResourceBuilder builder() {
        return UsuarioResource.builder()
                .nome("João")
                .sobrenome("Bezerra da Silva")
                .cpf("78664841209")
                .papel(Papel.ADMIN)
                .dataNascimento(LocalDate.of(2000, 6, 6))
                .email("joaobsilva@email.com")
                .celular("11999998888");
    }
}
