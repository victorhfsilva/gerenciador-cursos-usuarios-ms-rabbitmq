package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Professor;
import com.example.usuariosms.model.requests.ProfessorRequest;
import com.example.usuariosms.model.resources.ProfessorResource;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfessorMapper {
    Professor map(ProfessorRequest professorRequest);
    ProfessorResource map(Professor professor);
}
