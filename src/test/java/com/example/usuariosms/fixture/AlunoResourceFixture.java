package com.example.usuariosms.fixture;

import com.example.usuariosms.model.enums.Papel;
import com.example.usuariosms.model.resources.AlunoResource;

import java.time.LocalDate;
import java.util.UUID;

public interface AlunoResourceFixture {
    static AlunoResource buildValido() {
        return builder().build();
    }
    private static AlunoResource.AlunoResourceBuilder builder() {
        return AlunoResource.builder()
                .id(UUID.randomUUID())
                .nome("João")
                .sobrenome("Bezerra da Silva")
                .cpf("78664841209")
                .papel(Papel.ADMIN)
                .dataNascimento(LocalDate.of(2000, 6, 6))
                .email("joaobsilva@email.com")
                .celular("11999998888")
                .idEstudantil("1234567890");
    }
}
