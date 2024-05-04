package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.dto.RegistrarUsuarioRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioRegistrarUsuarioRequestMapper {

    Usuario registrarUsuarioRequestToUsuario(RegistrarUsuarioRequest registrarUsuarioRequest);

    RegistrarUsuarioRequest usuarioToRegistrarUsuarioRequest(Usuario usuario);

}
