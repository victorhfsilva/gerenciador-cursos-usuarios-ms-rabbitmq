package com.example.usuariosms.service;

import com.example.usuariosms.model.requests.AlunoRequest;
import com.example.usuariosms.model.resources.AlunoResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface AlunoService {
    AlunoResource save(AlunoRequest alunoRequest);
    AlunoResource findById(UUID id);
    Page<AlunoResource> findAll(Pageable pageable);
    AlunoResource update(UUID id, AlunoRequest alunoRequest);
    AlunoResource delete(UUID id);
}
