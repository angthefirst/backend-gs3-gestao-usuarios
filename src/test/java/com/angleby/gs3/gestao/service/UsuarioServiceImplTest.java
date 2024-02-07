package com.angleby.gs3.gestao.service;

import com.angleby.gs3.gestao.domain.constante.Permissao;
import com.angleby.gs3.gestao.domain.dto.*;
import com.angleby.gs3.gestao.domain.entity.Endereco;
import com.angleby.gs3.gestao.domain.entity.Usuario;
import com.angleby.gs3.gestao.repository.EnderecoRepository;
import com.angleby.gs3.gestao.repository.UsuarioRepository;
import com.angleby.gs3.gestao.service.impl.UsuarioServiceImpl;
import com.angleby.gs3.gestao.service.mapper.EnderecoMapper;
import com.angleby.gs3.gestao.service.mapper.UsuarioMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private EnderecoMapper enderecoMapper;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    void antesDaExecucao() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testeAtualizarUsuarioEPerfil() {
        EnderecoDTO enderecoDTO = EnderecoDTO.builder().rua("Symphony of the Night").build();
        Endereco endereco = Endereco.builder().id("uuid").rua("Symphony of the Night").build();
        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                .id("uuid")
                .nomeCompleto("Alucard")
                .login("somacruz")
                .senha("DownOfSorrow")
                .perfil(PerfilDTO.builder().id(1L).build())
                .endereco(enderecoDTO)
                .build();

        Usuario usuarioSalvo = Usuario.builder()
                .id("uuid")
                .nomeCompleto("Alucard")
                .login("somacruz")
                .senha("senhacriptografada")
                .build();

        RetornoDadosUsuarioDTO retornoEsperado = RetornoDadosUsuarioDTO.builder()
                .id("uuid")
                .nomeCompleto("Alucard")
                .login("somacruz")
                .perfil(PerfilDTO.builder().id(1L).descricao(Permissao.ADMIN).build())
                .endereco(RetornoEnderecoDTO.builder().rua("Symphony of the Night").build())
                .build();

        when(usuarioRepository.findById(usuarioDTO.id())).thenReturn(Optional.of(usuarioSalvo));
        when(usuarioMapper.dtoParaEntidade(usuarioDTO)).thenReturn(usuarioSalvo);
        when(enderecoMapper.dtoParaEntidade(enderecoDTO)).thenReturn(endereco);
        when(usuarioMapper.entidadeParaRetornoDadosUsuarioDTO(any(), any()))
                .thenReturn(retornoEsperado);

        RetornoDadosUsuarioDTO retorno = usuarioService.atualizarUsuarioEPerfil("uuid", usuarioDTO);

        assertEquals(retornoEsperado, retorno);
        assertEquals(Permissao.ADMIN, retornoEsperado.perfil().descricao());
    }

    @Test
    void testeBuscarUsuarioPorId() {
        Usuario usuario = Usuario.builder().id("idUsuario").build();
        Endereco endereco = Endereco.builder().id("idUsuario").rua("rua").build();

        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        when(enderecoRepository.findById(endereco.getId())).thenReturn(Optional.of(endereco));
        when(usuarioMapper.entidadeParaRetornoDadosUsuarioDTO(any(), any()))
                .thenReturn(RetornoDadosUsuarioDTO.builder().id("idUsuario").build());

        RetornoDadosUsuarioDTO retorno = usuarioService.buscarUsuarioPorId("idUsuario");

        assertEquals("idUsuario", retorno.id());
    }

    @Test
    void testeBuscarTodosOsUsuariosPaginados() {
        List<Endereco> enderecos = Collections.singletonList(Endereco.builder().bairro("Gama").build());

        Pageable pageable = Pageable.unpaged();
        Page<Endereco> pageEnderecos = new PageImpl<>(enderecos, pageable, enderecos.size());

        when(enderecoRepository.findAll(any(Pageable.class))).thenReturn(pageEnderecos);
        when(usuarioMapper.entidadeParaRetornoDadosUsuarioDTO(any(), any()))
                .thenReturn(RetornoDadosUsuarioDTO.builder().endereco(RetornoEnderecoDTO.builder().bairro("Gama").build()).build());

        Page<RetornoDadosUsuarioDTO> retorno = usuarioService.buscarTodosOsUsuariosPaginados(0, 10);

        assertEquals(1, retorno.getTotalElements());
        assertEquals("Gama", retorno.getContent().get(0).endereco().bairro());
    }

    @Test
    void testeAtualizarApenasUsuario() {
        EnderecoDTO enderecoDTO = EnderecoDTO.builder().cidade("Detroit").build();
        Endereco endereco = Endereco.builder().id("uuid").cidade("Detroit").build();
        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                .id("uuid")
                .nomeCompleto("All Mighty")
                .login("midorya")
                .senha("todoroki123")
                .endereco(enderecoDTO)
                .build();

        Usuario usuarioSalvo = Usuario.builder()
                .id("uuid")
                .nomeCompleto("All Mighty")
                .login("midorya")
                .senha("senhacriptografada")
                .build();

        RetornoDadosUsuarioDTO retornoEsperado = RetornoDadosUsuarioDTO.builder()
                .id("uuid")
                .nomeCompleto("All Mighty")
                .login("midorya")
                .perfil(PerfilDTO.builder().id(1L).build())
                .endereco(RetornoEnderecoDTO.builder().rua("Symphony of the Night").build())
                .build();

        when(usuarioRepository.findById(usuarioDTO.id())).thenReturn(Optional.of(usuarioSalvo));
        when(usuarioMapper.dtoParaEntidade(usuarioDTO)).thenReturn(usuarioSalvo);
        when(enderecoMapper.dtoParaEntidade(enderecoDTO)).thenReturn(endereco);
        when(usuarioMapper.entidadeParaRetornoDadosUsuarioDTO(any(), any()))
                .thenReturn(retornoEsperado);

        RetornoDadosUsuarioDTO retorno = usuarioService.atualizarApenasUsuario("uuid", usuarioDTO);

        assertEquals(retornoEsperado, retorno);
    }
}
