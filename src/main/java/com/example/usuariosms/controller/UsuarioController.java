package com.example.usuariosms.controller;

import com.example.usuariosms.model.requests.UsuarioRequest;
import com.example.usuariosms.model.resources.UsuarioResource;
import com.example.usuariosms.service.UsuarioService;
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
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioService usuarioService;
    private PagedResourcesAssembler<UsuarioResource> pagedResourcesAssembler;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioResource>> buscarUsuarioPorId(@PathVariable UUID id) {
        UsuarioResource usuarioResource = usuarioService.findById(id);
        return ResponseEntity.ok().body(EntityModel.of(usuarioResource));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<EntityModel<UsuarioResource>> buscarUsuarioPorCpf(@PathVariable String cpf) {
        UsuarioResource usuarioResource = usuarioService.findByCpf(cpf);
        return ResponseEntity.ok().body(EntityModel.of(usuarioResource));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<UsuarioResource>>> buscarTodosUsuarios(Pageable pageable){
        Page<UsuarioResource> usuarioResources = usuarioService.findAll(pageable);
        PagedModel<EntityModel<UsuarioResource>> pagedModel = pagedResourcesAssembler
                .toModel(usuarioResources, linkTo(methodOn(UsuarioController.class)
                        .buscarTodosUsuarios(pageable))
                        .withSelfRel());
        return ResponseEntity.ok().body(pagedModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<UsuarioResource>> registrarUsuario(@RequestBody @Valid UsuarioRequest usuarioDto) {
        UsuarioResource usuarioResource = usuarioService.save(usuarioDto);
        return ResponseEntity.created(null).body(EntityModel.of(usuarioResource));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioResource>> atualizarUsuario(@PathVariable UUID id, @RequestBody @Valid UsuarioRequest usuarioDto) {
        UsuarioResource usuarioResource = usuarioService.update(id, usuarioDto);
        return ResponseEntity.ok().body(EntityModel.of(usuarioResource));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioResource>> deletarUsuario(@PathVariable UUID id) {
        UsuarioResource usuarioResource = usuarioService.delete(id);
        return ResponseEntity.ok().body(EntityModel.of(usuarioResource));
    }

}
