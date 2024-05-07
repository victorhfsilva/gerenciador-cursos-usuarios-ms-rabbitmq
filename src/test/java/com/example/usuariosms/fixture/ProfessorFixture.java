package com.example.usuariosms.fixture;

import com.example.usuariosms.model.Professor;
import com.example.usuariosms.model.enums.Papel;
import java.time.LocalDate;
import java.util.UUID;

public interface ProfessorFixture {
    static Professor buildValido() {
        return builder().build();
    }
    private static Professor.ProfessorBuilder builder() {
        return Professor.professorBuilder()
                .id(UUID.randomUUID())
                .nome("João")
                .sobrenome("Bezerra da Silva")
                .cpf("78664841209")
                .senha("Senha@123")
                .papel(Papel.ADMIN)
                .dataNascimento(LocalDate.of(2000, 6, 6))
                .email("joaobsilva@email.com")
                .celular("11999998888")
                .endereco(EnderecoFixture.buildValido())
                .numeroCTPS("1234567890")
                .serieCTPS("1234567890")
                .numeroPIS("12345678901")
                .remuneracao(10000.0);
    }
}
