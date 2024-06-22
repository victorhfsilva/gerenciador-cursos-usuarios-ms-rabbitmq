package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Aluno;
import com.example.usuariosms.model.requests.AlunoRequest;
import com.example.usuariosms.model.resources.AlunoResource;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AlunoMapper {
    Aluno map(AlunoRequest alunoRequest);
    AlunoResource map(Aluno aluno);
}
