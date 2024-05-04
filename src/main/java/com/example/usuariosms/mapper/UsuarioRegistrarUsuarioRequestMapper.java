package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.dto.RegistrarUsuarioRequest;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsuarioRegistrarUsuarioRequestMapper {

    private EnderecoRegistrarEnderecoRequestMapper enderecoRegistrarEnderecoRequestMapper;
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuarioRequestToUsuario(RegistrarUsuarioRequest registrarUsuarioRequest){
        return Usuario.builder()
                .nome(registrarUsuarioRequest.nome())
                .sobrenome(registrarUsuarioRequest.sobrenome())
                .cpf(registrarUsuarioRequest.cpf())
                .senha(passwordEncoder.encode(registrarUsuarioRequest.senha()))
                .papel(registrarUsuarioRequest.papel())
                .dataNascimento(registrarUsuarioRequest.dataNascimento())
                .email(registrarUsuarioRequest.email())
                .celular(registrarUsuarioRequest.celular())
                .endereco(enderecoRegistrarEnderecoRequestMapper
                                .registrarEnderecoRequestToEndereco(registrarUsuarioRequest.endereco()))
                .build();
    }


}
