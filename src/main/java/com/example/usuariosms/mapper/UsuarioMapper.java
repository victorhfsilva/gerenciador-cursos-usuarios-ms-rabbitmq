package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.requests.UsuarioRequest;
import com.example.usuariosms.model.resources.UsuarioResource;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper {
    UsuarioResource map(Usuario usuario);
    Usuario map(UsuarioRequest usuarioRequest);
}
