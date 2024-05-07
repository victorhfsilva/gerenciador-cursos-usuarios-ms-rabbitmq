package com.example.usuariosms.fixture;

import com.example.usuariosms.model.Aluno;
import com.example.usuariosms.model.enums.Papel;

import java.time.LocalDate;
import java.util.UUID;

public interface AlunoFixture {

    static Aluno buildValido() {
        return builder().build();
    }
    private static Aluno.AlunoBuilder builder() {
        return Aluno.alunoBuilder()
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
                .idEstudantil("1234567890");
    }

}
