package com.angleby.gs3.gestao.service.impl;

import com.angleby.gs3.gestao.config.TokenService;
import com.angleby.gs3.gestao.domain.dto.AutenticacaoDTO;
import com.angleby.gs3.gestao.domain.dto.RetornoLoginDTO;
import com.angleby.gs3.gestao.domain.dto.UsuarioDTO;
import com.angleby.gs3.gestao.domain.entity.Usuario;
import com.angleby.gs3.gestao.repository.EnderecoRepository;
import com.angleby.gs3.gestao.repository.UsuarioRepository;
import com.angleby.gs3.gestao.service.AutenticacaoService;
import com.angleby.gs3.gestao.service.mapper.EnderecoMapper;
import com.angleby.gs3.gestao.service.mapper.UsuarioMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AutenticacaoServiceImpl implements AutenticacaoService {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    private UsuarioRepository usuarioRepository;
    private EnderecoRepository enderecoRepository;

    private UsuarioMapper usuarioMapper;
    private EnderecoMapper enderecoMapper;

    @Override
    public RetornoLoginDTO realizarLogin(AutenticacaoDTO autenticacaoDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(autenticacaoDTO.login(), autenticacaoDTO.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return RetornoLoginDTO.builder().token(tokenService.gerarToken((Usuario) auth.getPrincipal())).build();
    }

    @Override
    public void realizarRegistro(UsuarioDTO usuarioDTO) {
        if(this.usuarioRepository.findByLogin(usuarioDTO.login()) != null)
            throw new RuntimeException("Login já utilizado.");

        Usuario usuario = this.usuarioRepository.save(usuarioMapper.dtoParaEntidade(usuarioDTO));
        this.enderecoRepository.save(enderecoMapper.dtoParaEntidade(usuarioDTO.endereco(), usuario));
    }
}
