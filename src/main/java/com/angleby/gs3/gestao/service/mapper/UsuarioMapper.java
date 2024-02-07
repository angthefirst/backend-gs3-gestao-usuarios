package com.angleby.gs3.gestao.service.mapper;

import com.angleby.gs3.gestao.domain.dto.UsuarioDTO;
import com.angleby.gs3.gestao.domain.entity.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UsuarioMapper implements MapperAbstrato<Usuario, UsuarioDTO> {

    private PerfilMapper perfilMapper;

    @Override
    public UsuarioDTO entidadeParaDTO(Usuario entidade) {
        return UsuarioDTO.builder()
                .login(entidade.getLogin())
                .senha(entidade.getSenha())
                .nomeCompleto(entidade.getNomeCompleto())
                .email(entidade.getEmail())
                .perfil(perfilMapper.entidadeParaDTO(entidade.getPerfil()))
                .build();
    }

    @Override
    public Usuario dtoParaEntidade(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .login(usuarioDTO.login())
                .senha(new BCryptPasswordEncoder().encode(usuarioDTO.senha()))
                .nomeCompleto(usuarioDTO.nomeCompleto())
                .email(usuarioDTO.email())
                .perfil(perfilMapper.dtoParaEntidade(usuarioDTO.perfil()))
                .build();
    }

}
