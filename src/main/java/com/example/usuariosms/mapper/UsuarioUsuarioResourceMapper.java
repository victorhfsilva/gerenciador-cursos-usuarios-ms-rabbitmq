package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.resources.UsuarioResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioUsuarioResourceMapper {
    UsuarioResource usuarioToUsuarioResource(Usuario usuario);
}
