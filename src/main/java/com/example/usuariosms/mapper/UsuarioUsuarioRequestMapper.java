package com.example.usuariosms.mapper;

import com.example.usuariosms.model.Usuario;
import com.example.usuariosms.model.dto.UsuarioRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsuarioUsuarioRequestMapper {

    private EnderecoEnderecoRequestMapper enderecoEnderecoRequestMapper;
    private PasswordEncoder passwordEncoder;

    public Usuario usuarioRequestToUsuario(UsuarioRequest usuarioRequest){
        return Usuario.builder()
                .nome(usuarioRequest.nome())
                .sobrenome(usuarioRequest.sobrenome())
                .cpf(usuarioRequest.cpf())
                .senha(passwordEncoder.encode(usuarioRequest.senha()))
                .papel(usuarioRequest.papel())
                .dataNascimento(usuarioRequest.dataNascimento())
                .email(usuarioRequest.email())
                .celular(usuarioRequest.celular())
                .endereco(enderecoEnderecoRequestMapper
                                .enderecoRequestToEndereco(usuarioRequest.endereco()))
                .build();
    }


}
