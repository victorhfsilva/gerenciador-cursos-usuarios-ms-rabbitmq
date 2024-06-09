package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Endereco;
import com.example.usuariosms.model.resources.EnderecoResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoEnderecoResourceMapper {
    EnderecoResource enderecoToEnderecoResponse(Endereco endereco);
}
