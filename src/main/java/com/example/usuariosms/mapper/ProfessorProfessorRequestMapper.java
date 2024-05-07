package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Professor;
import com.example.usuariosms.model.dto.ProfessorRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProfessorProfessorRequestMapper {

    private EnderecoEnderecoRequestMapper enderecoEnderecoRequestMapper;
    private PasswordEncoder passwordEncoder;

    public Professor professorRequestToProfessor(ProfessorRequest professorRequest){
        return Professor.professorBuilder()
                .nome(professorRequest.nome())
                .sobrenome(professorRequest.sobrenome())
                .cpf(professorRequest.cpf())
                .senha(passwordEncoder.encode(professorRequest.senha()))
                .papel(professorRequest.papel())
                .dataNascimento(professorRequest.dataNascimento())
                .email(professorRequest.email())
                .celular(professorRequest.celular())
                .endereco(enderecoEnderecoRequestMapper
                        .enderecoRequestToEndereco(professorRequest.endereco()))
                .numeroCTPS(professorRequest.numeroCTPS())
                .serieCTPS(professorRequest.serieCTPS())
                .numeroPIS(professorRequest.numeroPIS())
                .remuneracao(professorRequest.remuneracao())
                .build();
    }


}