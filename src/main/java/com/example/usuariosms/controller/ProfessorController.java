package com.example.usuariosms.controller;

import com.example.usuariosms.model.requests.ProfessorRequest;
import com.example.usuariosms.model.resources.ProfessorResource;
import com.example.usuariosms.service.IProfessorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/professores")
@AllArgsConstructor
public class ProfessorController {

    private IProfessorService professorService;
    private PagedResourcesAssembler<ProfessorResource> pagedResourcesAssembler;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ProfessorResource>> buscarProfessorPorId(@PathVariable UUID id) {
        ProfessorResource professorResource = professorService.findById(id);
        return ResponseEntity.ok().body(EntityModel.of(professorResource));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ProfessorResource>>> buscarTodosProfessors(Pageable pageable){
        Page<ProfessorResource> professorResources = professorService.findAll(pageable);
        PagedModel<EntityModel<ProfessorResource>> pagedModel = pagedResourcesAssembler
                .toModel(professorResources, linkTo(methodOn(ProfessorController.class)
                        .buscarTodosProfessors(pageable))
                        .withSelfRel());
        return ResponseEntity.ok().body(pagedModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<ProfessorResource>> registrarProfessor(@RequestBody @Valid ProfessorRequest professorDto) {
        ProfessorResource professorResource = professorService.save(professorDto);
        return ResponseEntity.created(null).body(EntityModel.of(professorResource));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ProfessorResource>> atualizarProfessor(@PathVariable UUID id, @RequestBody @Valid ProfessorRequest professorDto) {
        ProfessorResource professorResource = professorService.update(id, professorDto);
        return ResponseEntity.ok().body(EntityModel.of(professorResource));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EntityModel<ProfessorResource>> deletarProfessor(@PathVariable UUID id) {
        ProfessorResource professorResource = professorService.delete(id);
        return ResponseEntity.ok().body(EntityModel.of(professorResource));
    }

}