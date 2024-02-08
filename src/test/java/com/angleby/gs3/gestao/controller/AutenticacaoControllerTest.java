package com.angleby.gs3.gestao.controller;

import com.angleby.gs3.gestao.domain.dto.AutenticacaoDTO;
import com.angleby.gs3.gestao.domain.dto.RetornoLoginDTO;
import com.angleby.gs3.gestao.domain.dto.UsuarioDTO;
import com.angleby.gs3.gestao.service.AutenticacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
class AutenticacaoControllerTest {

    @MockBean
    private AutenticacaoService autenticacaoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLogin() throws Exception {
        AutenticacaoDTO autenticacaoDTO = new AutenticacaoDTO("username", "password");
        RetornoLoginDTO retornoLoginDTO = new RetornoLoginDTO("token");

        when(autenticacaoService.realizarLogin(autenticacaoDTO)).thenReturn(retornoLoginDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(autenticacaoDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").value(retornoLoginDTO.token()));
    }

    @Test
    void testRegistro() throws Exception {
        UsuarioDTO usuarioDTO = UsuarioDTO.builder().login("username").senha("password").email("email").build();

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/registro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(usuarioDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(autenticacaoService, times(1)).realizarRegistro(usuarioDTO);
    }

}

