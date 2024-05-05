package com.example.usuariosms.controller;

import com.example.usuariosms.model.dto.UsuarioRequest;
import com.example.usuariosms.model.resources.UsuarioResource;
import com.example.usuariosms.service.impl.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    UsuarioService usuarioService;

    @GetMapping("/{id}")
    public EntityModel<UsuarioResource> buscarUsuarioPorId(@PathVariable UUID id) {
        UsuarioResource usuarioResource = usuarioService.findById(id);
        return EntityModel.of(usuarioResource);
    }

    @GetMapping
    public CollectionModel<UsuarioResource> buscarTodosUsuarios(Pageable pageable){
        Page<UsuarioResource> usuarioResources = usuarioService.findAll(pageable);
        return CollectionModel.of(usuarioResources, linkTo(methodOn(UsuarioController.class).buscarTodosUsuarios(pageable)).withSelfRel());
    }

    @PostMapping
    public EntityModel<UsuarioResource> registrarUsuario(@RequestBody UsuarioRequest usuarioDto) {
        UsuarioResource usuarioResource = usuarioService.save(usuarioDto);
        return EntityModel.of(usuarioResource);
    }

    @PutMapping("/{id}")
    public EntityModel<UsuarioResource> atualizarUsuario(@PathVariable UUID id, @RequestBody UsuarioRequest usuarioDto) {
        UsuarioResource usuarioResource = usuarioService.update(id, usuarioDto);
        return EntityModel.of(usuarioResource);
    }

    @DeleteMapping("/{id}")
    public EntityModel<UsuarioResource> deletarUsuario(@PathVariable UUID id) {
        UsuarioResource usuarioResource = usuarioService.delete(id);
        return EntityModel.of(usuarioResource);
    }

}
