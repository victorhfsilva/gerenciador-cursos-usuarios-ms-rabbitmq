package com.example.usuariosms.controller;

import com.example.usuariosms.model.dto.EnderecoRequest;
import com.example.usuariosms.model.resources.EnderecoResource;
import com.example.usuariosms.service.IEnderecoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/enderecos")
@AllArgsConstructor
public class EnderecoController {

    private IEnderecoService enderecoService;

    @GetMapping("/{usuarioId}")
    public EntityModel<EnderecoResource> buscarEnderecosPorUsuarioId(@PathVariable UUID usuarioId) {
        EnderecoResource enderecoResource = enderecoService.findByUsuarioId(usuarioId);
        return EntityModel.of(enderecoResource);
    }

    @PutMapping("/{usuarioId}")
    public EntityModel<EnderecoResource> atualizarEndereco(@PathVariable UUID usuarioId, @RequestBody @Valid EnderecoRequest enderecoRequest) {
        EnderecoResource enderecoResource = enderecoService.update(usuarioId, enderecoRequest);
        return EntityModel.of(enderecoResource);
    }


}
