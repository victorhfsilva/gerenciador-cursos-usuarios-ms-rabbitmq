package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.resources.UsuarioResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioUsuarioResourceMapper {
    UsuarioResource usuarioToUsuarioResource(Usuario usuario);
}
