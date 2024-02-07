package com.angleby.gs3.gestao.service;

import com.angleby.gs3.gestao.domain.dto.AutenticacaoDTO;
import com.angleby.gs3.gestao.domain.dto.RetornoLoginDTO;
import com.angleby.gs3.gestao.domain.dto.UsuarioDTO;

public interface AutenticacaoService {
    RetornoLoginDTO realizarLogin(AutenticacaoDTO autenticacaoDTO);

    void realizarRegistro(UsuarioDTO usuarioDTO);
}
