package com.angleby.gs3.gestao.service;

import com.angleby.gs3.gestao.domain.dto.RetornoDadosUsuarioDTO;
import com.angleby.gs3.gestao.domain.dto.UsuarioDTO;
import org.springframework.data.domain.Page;

public interface UsuarioService {

    RetornoDadosUsuarioDTO atualizarUsuarioEPerfil(String idUsuario, UsuarioDTO usuarioDTO);

    RetornoDadosUsuarioDTO atualizarApenasUsuario(String idUsuario, UsuarioDTO usuarioDTO);

    RetornoDadosUsuarioDTO buscarUsuarioPorId(String idUsuario);

    Page<RetornoDadosUsuarioDTO> buscarTodosOsUsuariosPaginados(Integer pagina, Integer tamanhoPagina);
}
