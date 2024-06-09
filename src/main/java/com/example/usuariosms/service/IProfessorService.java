package com.example.usuariosms.service;

import com.example.usuariosms.model.requests.ProfessorRequest;
import com.example.usuariosms.model.resources.ProfessorResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface IProfessorService {
    ProfessorResource save(ProfessorRequest professorRequest);
    ProfessorResource findById(UUID id);
    Page<ProfessorResource> findAll(Pageable pageable);
    ProfessorResource update(UUID id, ProfessorRequest professorRequest);
    ProfessorResource delete(UUID id);
}
