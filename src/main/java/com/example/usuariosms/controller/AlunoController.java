package com.example.usuariosms.controller;

import com.example.usuariosms.model.dto.AlunoRequest;
import com.example.usuariosms.model.resources.AlunoResource;
import com.example.usuariosms.service.impl.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/alunos")
@AllArgsConstructor
public class AlunoController {

    private AlunoService alunoService;
    private PagedResourcesAssembler<AlunoResource> pagedResourcesAssembler;
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<AlunoResource>> buscarAlunoPorId(@PathVariable UUID id) {
        AlunoResource alunoResource = alunoService.findById(id);
        return ResponseEntity.ok().body(EntityModel.of(alunoResource));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<AlunoResource>>> buscarTodosAlunos(Pageable pageable){
        Page<AlunoResource> alunoResources = alunoService.findAll(pageable);
        PagedModel<EntityModel<AlunoResource>> pagedModel = pagedResourcesAssembler
                .toModel(alunoResources, linkTo(methodOn(AlunoController.class)
                        .buscarTodosAlunos(pageable))
                        .withSelfRel());
        return ResponseEntity.ok().body(pagedModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<AlunoResource>> registrarAluno(@RequestBody AlunoRequest alunoDto) {
        AlunoResource alunoResource = alunoService.save(alunoDto);
        return ResponseEntity.created(null).body(EntityModel.of(alunoResource));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<AlunoResource>> atualizarAluno(@PathVariable UUID id, @RequestBody AlunoRequest alunoDto) {
        AlunoResource alunoResource = alunoService.update(id, alunoDto);
        return ResponseEntity.ok().body(EntityModel.of(alunoResource));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EntityModel<AlunoResource>> deletarAluno(@PathVariable UUID id) {
        AlunoResource alunoResource = alunoService.delete(id);
        return ResponseEntity.ok().body(EntityModel.of(alunoResource));
    }

}
