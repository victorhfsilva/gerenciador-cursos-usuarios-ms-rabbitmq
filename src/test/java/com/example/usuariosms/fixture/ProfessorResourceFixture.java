package com.example.usuariosms.fixture;

import com.example.usuariosms.model.Professor;
import com.example.usuariosms.model.enums.Papel;
import com.example.usuariosms.model.resources.ProfessorResource;

import java.time.LocalDate;
import java.util.UUID;

public interface ProfessorResourceFixture {
    static ProfessorResource buildValido() {
        return builder().build();
    }
    private static ProfessorResource.ProfessorResourceBuilder builder() {
        return ProfessorResource.builder()
                .id(UUID.randomUUID())
                .nome("João")
                .sobrenome("Bezerra da Silva")
                .cpf("78664841209")
                .papel(Papel.ADMIN)
                .dataNascimento(LocalDate.of(2000, 6, 6))
                .email("joaobsilva@email.com")
                .celular("11999998888")
                .numeroCTPS("1234567890")
                .serieCTPS("1234567890")
                .numeroPIS("12345678901")
                .remuneracao(10000.0);
    }
}
