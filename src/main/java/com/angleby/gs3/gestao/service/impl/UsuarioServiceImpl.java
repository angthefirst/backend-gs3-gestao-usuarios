package com.angleby.gs3.gestao.service.impl;

import com.angleby.gs3.gestao.domain.dto.RetornoDadosUsuarioDTO;
import com.angleby.gs3.gestao.domain.dto.UsuarioDTO;
import com.angleby.gs3.gestao.domain.entity.Endereco;
import com.angleby.gs3.gestao.domain.entity.Usuario;
import com.angleby.gs3.gestao.exception.UsuarioNotFoundException;
import com.angleby.gs3.gestao.repository.EnderecoRepository;
import com.angleby.gs3.gestao.repository.UsuarioRepository;
import com.angleby.gs3.gestao.service.UsuarioService;
import com.angleby.gs3.gestao.service.mapper.EnderecoMapper;
import com.angleby.gs3.gestao.service.mapper.UsuarioMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepository;
    private EnderecoRepository enderecoRepository;

    private UsuarioMapper usuarioMapper;
    private EnderecoMapper enderecoMapper;

    @Override
    @Transactional
    public RetornoDadosUsuarioDTO atualizarUsuarioEPerfil(String idUsuario, UsuarioDTO usuarioDTO) {
        Usuario usuarioSalvo = getUsuarioPorId(idUsuario);

        Usuario usuarioComDadosAtualizados = parseEntidadeUsuarioComDadosAtualizadosByPerfilAdmin(usuarioDTO, usuarioSalvo);
        Endereco enderecoComDadosAtualizados = parseEntidadeEnderecoComDadosAtualizados(idUsuario, usuarioDTO);

        usuarioRepository.save(usuarioComDadosAtualizados);
        enderecoRepository.save(enderecoComDadosAtualizados);

        return usuarioMapper.entidadeParaRetornoDadosUsuarioDTO(
                usuarioComDadosAtualizados,
                enderecoMapper.entidadeParaRetornoEnderecoDTO(enderecoComDadosAtualizados)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public RetornoDadosUsuarioDTO buscarUsuarioPorId(String idUsuario) {
        Usuario usuario = getUsuarioPorId(idUsuario);
        Endereco endereco = enderecoRepository.findById(idUsuario)
                .orElseThrow(UsuarioNotFoundException::new);

        return usuarioMapper.entidadeParaRetornoDadosUsuarioDTO(usuario, enderecoMapper.entidadeParaRetornoEnderecoDTO(endereco));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RetornoDadosUsuarioDTO> buscarTodosOsUsuariosPaginados(Integer pagina, Integer tamanhoPagina) {
        Pageable pageable = PageRequest.of(pagina, tamanhoPagina);
        Page<Endereco> paginaEnderecos = enderecoRepository.findAll(pageable);

        List<RetornoDadosUsuarioDTO> listaRetorno = paginaEnderecos.getContent().stream()
                .map(endereco ->
                        usuarioMapper.entidadeParaRetornoDadosUsuarioDTO(
                                endereco.getUsuario(),
                                enderecoMapper.entidadeParaRetornoEnderecoDTO(endereco)
                        )
                )
                .collect(Collectors.toList());

        return new PageImpl<>(listaRetorno, pageable, paginaEnderecos.getTotalElements());
    }

    @Override
    @Transactional
    public RetornoDadosUsuarioDTO atualizarApenasUsuario(String idUsuario, UsuarioDTO usuarioDTO) {
        Usuario usuarioSalvo = getUsuarioPorId(usuarioDTO.id());

        Usuario usuarioComDadosAtualizados = parseEntidadeUsuarioComDadosAtualizadosByPerfilComum(usuarioDTO, usuarioSalvo);
        Endereco enderecoComDadosAtualizados = parseEntidadeEnderecoComDadosAtualizados(idUsuario, usuarioDTO);

        usuarioRepository.save(usuarioComDadosAtualizados);
        enderecoRepository.save(enderecoComDadosAtualizados);

        return usuarioMapper.entidadeParaRetornoDadosUsuarioDTO(
                usuarioComDadosAtualizados,
                enderecoMapper.entidadeParaRetornoEnderecoDTO(enderecoComDadosAtualizados)
        );
    }

    private Usuario getUsuarioPorId(String id) {
        return usuarioRepository.findById(id)
                .orElseThrow(UsuarioNotFoundException::new);
    }

    private Usuario parseEntidadeUsuarioComDadosAtualizadosByPerfilAdmin(UsuarioDTO usuarioDTO, Usuario usuarioSalvo) {
        Usuario usuarioComDadosAtualizados = usuarioMapper.dtoParaEntidade(usuarioDTO);
        usuarioComDadosAtualizados.setSenha(usuarioSalvo.getSenha());
        return usuarioComDadosAtualizados;
    }

    private Usuario parseEntidadeUsuarioComDadosAtualizadosByPerfilComum(UsuarioDTO usuarioDTO, Usuario usuarioSalvo) {
        Usuario usuarioComDadosAtualizados = usuarioMapper.dtoParaEntidade(usuarioDTO);
        usuarioComDadosAtualizados.setSenha(usuarioSalvo.getSenha());
        usuarioComDadosAtualizados.setPerfil(usuarioSalvo.getPerfil());
        return usuarioComDadosAtualizados;
    }

    private Endereco parseEntidadeEnderecoComDadosAtualizados(String idUsuario, UsuarioDTO usuarioDTO) {
        Endereco enderecoComDadosAtualizados = enderecoMapper.dtoParaEntidade(usuarioDTO.endereco());
        enderecoComDadosAtualizados.setUsuario(Usuario.builder().id(idUsuario).build());
        enderecoComDadosAtualizados.setId(idUsuario);
        return enderecoComDadosAtualizados;
    }
}
