package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Endereco;
import com.example.usuariosms.model.resources.EnderecoResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnderecoEnderecoResourceMapper {
    EnderecoResource enderecoToEnderecoResponse(Endereco endereco);
}
