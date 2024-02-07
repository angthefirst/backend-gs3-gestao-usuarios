package com.angleby.gs3.gestao.service.mapper;

import com.angleby.gs3.gestao.domain.constante.PerfilEnum;
import com.angleby.gs3.gestao.domain.dto.EnderecoDTO;
import com.angleby.gs3.gestao.domain.dto.RetornoDadosUsuarioDTO;
import com.angleby.gs3.gestao.domain.dto.RetornoEnderecoDTO;
import com.angleby.gs3.gestao.domain.dto.UsuarioDTO;
import com.angleby.gs3.gestao.domain.entity.Endereco;
import com.angleby.gs3.gestao.domain.entity.Perfil;
import com.angleby.gs3.gestao.domain.entity.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Component
public class UsuarioMapper implements MapperAbstrato<Usuario, UsuarioDTO> {

    private PerfilMapper perfilMapper;

    @Override
    public UsuarioDTO entidadeParaDTO(Usuario entidade) {
        return UsuarioDTO.builder()
                .id(entidade.getId())
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
                .id(usuarioDTO.id())
                .login(usuarioDTO.login())
                .senha(new BCryptPasswordEncoder().encode(usuarioDTO.senha()))
                .nomeCompleto(usuarioDTO.nomeCompleto())
                .email(usuarioDTO.email())
                .perfil(perfilMapper.dtoParaEntidade(usuarioDTO.perfil()))
                .build();
    }

    public Usuario dtoParaEntidadePerfilComum(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .id(usuarioDTO.id())
                .login(usuarioDTO.login())
                .senha(new BCryptPasswordEncoder().encode(usuarioDTO.senha()))
                .nomeCompleto(usuarioDTO.nomeCompleto())
                .email(usuarioDTO.email())
                .perfil(Perfil.builder().id(PerfilEnum.COMUM.getId()).build())
                .build();
    }

    public RetornoDadosUsuarioDTO entidadeParaRetornoDadosUsuarioDTO(Usuario entidade, RetornoEnderecoDTO retornoEnderecoDTO) {
        return RetornoDadosUsuarioDTO.builder()
                .id(entidade.getId())
                .login(entidade.getLogin())
                .nomeCompleto(entidade.getNomeCompleto())
                .email(entidade.getEmail())
                .endereco(retornoEnderecoDTO)
                .perfil(perfilMapper.entidadeParaDTO(entidade.getPerfil()))
                .build();
    }

}
