package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.requests.UsuarioRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioUsuarioRequestMapper {
    public Usuario usuarioRequestToUsuario(UsuarioRequest usuarioRequest);
}
