package com.example.usuariosms.fixture;

import com.example.usuariosms.model.dto.AlunoRequest;
import com.example.usuariosms.model.enums.Papel;
import java.time.LocalDate;

public interface AlunoRequestFixture {

    static AlunoRequest buildValido() {
        return builder().build();
    }
    private static AlunoRequest.AlunoRequestBuilder builder() {
        return AlunoRequest.builder()
                .nome("João")
                .sobrenome("Bezerra da Silva")
                .cpf("78664841209")
                .senha("Senha@123")
                .papel(Papel.ADMIN)
                .dataNascimento(LocalDate.of(2000, 6, 6))
                .email("joaobsilva@email.com")
                .celular("11999998888")
                .endereco(EnderecoRequestFixture.buildValido())
                .idEstudantil("1234567890");
    }

}
