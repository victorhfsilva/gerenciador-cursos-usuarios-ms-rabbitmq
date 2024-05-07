package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Professor;
import com.example.usuariosms.model.resources.ProfessorResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfessorProfessorResourceMapper {
    ProfessorResource professorToProfessorResource(Professor professor);
}