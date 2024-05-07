package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Aluno;
import com.example.usuariosms.model.resources.AlunoResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlunoAlunoResourceMapper {
    AlunoResource alunoToAlunoResource(Aluno aluno);
}
