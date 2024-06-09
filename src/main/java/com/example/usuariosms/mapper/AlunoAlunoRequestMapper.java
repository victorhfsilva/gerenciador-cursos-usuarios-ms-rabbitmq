package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Aluno;
import com.example.usuariosms.model.requests.AlunoRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlunoAlunoRequestMapper {
    public Aluno alunoRequestToAluno(AlunoRequest alunoRequest);
}
