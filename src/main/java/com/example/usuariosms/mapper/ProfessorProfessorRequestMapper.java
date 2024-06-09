package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Professor;
import com.example.usuariosms.model.requests.ProfessorRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfessorProfessorRequestMapper {
    public Professor professorRequestToProfessor(ProfessorRequest professorRequest);
}