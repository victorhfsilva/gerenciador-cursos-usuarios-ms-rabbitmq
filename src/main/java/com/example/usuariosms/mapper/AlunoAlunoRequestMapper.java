package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Aluno;
import com.example.usuariosms.model.dto.AlunoRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AlunoAlunoRequestMapper {

    private EnderecoEnderecoRequestMapper enderecoEnderecoRequestMapper;
    private PasswordEncoder passwordEncoder;

    public Aluno alunoRequestToAluno(AlunoRequest alunoRequest){
        return Aluno.alunoBuilder()
                .nome(alunoRequest.nome())
                .sobrenome(alunoRequest.sobrenome())
                .cpf(alunoRequest.cpf())
                .senha(passwordEncoder.encode(alunoRequest.senha()))
                .papel(alunoRequest.papel())
                .dataNascimento(alunoRequest.dataNascimento())
                .email(alunoRequest.email())
                .celular(alunoRequest.celular())
                .endereco(enderecoEnderecoRequestMapper
                        .enderecoRequestToEndereco(alunoRequest.endereco()))
                .idEstudantil(alunoRequest.idEstudantil())
                .build();
    }
}
