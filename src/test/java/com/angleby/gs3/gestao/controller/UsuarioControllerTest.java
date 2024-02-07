package com.angleby.gs3.gestao.controller;

import com.angleby.gs3.gestao.domain.constante.Permissao;
import com.angleby.gs3.gestao.domain.dto.EnderecoDTO;
import com.angleby.gs3.gestao.domain.dto.PerfilDTO;
import com.angleby.gs3.gestao.domain.dto.RetornoDadosUsuarioDTO;
import com.angleby.gs3.gestao.domain.dto.UsuarioDTO;
import com.angleby.gs3.gestao.domain.entity.Usuario;
import com.angleby.gs3.gestao.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
class UsuarioControllerTest {

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(Permissao.PERFIL_ADMIN)
    @Test
    void testObterUsuarioPorIdByAdmin() throws Exception {
        RetornoDadosUsuarioDTO retornoMock = RetornoDadosUsuarioDTO.builder().id("1").build();
        when(usuarioService.buscarUsuarioPorId(anyString())).thenReturn(retornoMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/{idUsuario}", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(retornoMock.id()));
    }

    @WithMockUser(Permissao.PERFIL_COMUM)
    @Test
    void testObterUsuarioPorIdByComum() throws Exception {
        RetornoDadosUsuarioDTO retornoMock = RetornoDadosUsuarioDTO.builder().id("1").build();
        when(usuarioService.buscarUsuarioPorId(anyString())).thenReturn(retornoMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/{idUsuario}", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(retornoMock.id()));
    }

    @WithMockUser(Permissao.PERFIL_ADMIN)
    @Test
    void testObterTodosOsUsuariosPaginados() throws Exception {
        Page<RetornoDadosUsuarioDTO> pageMock = new PageImpl<>(Collections.emptyList());
        when(usuarioService.buscarTodosOsUsuariosPaginados(anyInt(), anyInt())).thenReturn(pageMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @WithMockUser(Permissao.PERFIL_ADMIN)
    @Test
    void testAtualizarUsuarioEPerfil() throws Exception {
        RetornoDadosUsuarioDTO retornoMock = RetornoDadosUsuarioDTO.builder().id("1").build();
        when(usuarioService.atualizarUsuarioEPerfil(anyString(), any())).thenReturn(retornoMock);

        mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/admin/{idUsuario}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper()
                                .writeValueAsBytes(UsuarioDTO.builder()
                                        .id("1")
                                        .senha("passwd")
                                        .perfil(PerfilDTO.builder().build())
                                        .endereco(EnderecoDTO.builder().build())
                                        .build())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(retornoMock.id()));
    }

    @WithMockUser(Permissao.PERFIL_COMUM)
    @Test
    void testAtualizarUsuario() throws Exception {
        RetornoDadosUsuarioDTO retornoMock = RetornoDadosUsuarioDTO.builder().id("uuid").build();
        when(usuarioService.atualizarApenasUsuario(anyString(), any())).thenReturn(retornoMock);

        mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/comum/{idUsuario}", "uuid")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(UsuarioDTO.builder().id("uuid").build())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(retornoMock.id()));
    }
}

