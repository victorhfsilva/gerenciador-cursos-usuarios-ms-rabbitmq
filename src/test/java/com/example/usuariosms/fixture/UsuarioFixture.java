package com.example.usuariosms.fixture;

import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.dto.RegistrarUsuarioRequest;
import com.example.usuariosms.model.enums.Papel;

import java.time.LocalDate;
import java.util.UUID;

public interface UsuarioFixture {
    static Usuario buildValido() {
        return builder().build();
    }
    static Usuario.UsuarioBuilder builder() {
        return Usuario.builder()
                .id(UUID.randomUUID())
                .nome("João")
                .sobrenome("Bezerra da Silva")
                .cpf("78664841209")
                .senha("Senha@123")
                .papel(Papel.ADMIN)
                .dataNascimento(LocalDate.of(2000, 6, 6))
                .email("joaobsilva@email.com")
                .celular("11999998888");
    }
}
