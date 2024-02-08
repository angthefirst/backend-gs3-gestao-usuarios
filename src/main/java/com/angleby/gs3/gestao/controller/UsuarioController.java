package com.angleby.gs3.gestao.controller;

import com.angleby.gs3.gestao.domain.constante.Permissao;
import com.angleby.gs3.gestao.domain.dto.RetornoDadosUsuarioDTO;
import com.angleby.gs3.gestao.domain.dto.UsuarioDTO;
import com.angleby.gs3.gestao.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UsuarioController", description = "Operações relacionadas a usuários")
@AllArgsConstructor
@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Operation(summary = "Obter usuário por ID", description = "Obtém dados do usuário por meio do ID")
    @PreAuthorize(Permissao.PERFIL_COMUM_E_ADMIN)
    @GetMapping("/{idUsuario}")
    public ResponseEntity<RetornoDadosUsuarioDTO> obterUsuarioPorId(@PathVariable String idUsuario) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(idUsuario));
    }

    @Operation(summary = "Obter todos os usuários", description = "Obtém todos os usuários cadastrados no sistema de forma paginada")
    @PreAuthorize(Permissao.PERFIL_ADMIN)
    @GetMapping
    public ResponseEntity<Page<RetornoDadosUsuarioDTO>> obterTodosOsUsuariosPaginados(
            @RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "10") Integer tamanhoPagina) {
        return ResponseEntity.ok(usuarioService.buscarTodosOsUsuariosPaginados(pagina, tamanhoPagina));
    }

    @Operation(summary = "Atualizar usuário e perfil", description = "Atualiza os dados do usuário e de perfil")
    @PreAuthorize(Permissao.PERFIL_ADMIN)
    @PutMapping("/admin/{idUsuario}")
    public ResponseEntity<RetornoDadosUsuarioDTO> atualizarUsuarioEPerfil(@PathVariable String idUsuario, @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.atualizarUsuarioEPerfil(idUsuario, usuarioDTO));
    }

    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados do usuário sem alterar o perfil")
    @PreAuthorize(Permissao.PERFIL_COMUM)
    @PutMapping("/comum/{idUsuario}")
    public ResponseEntity<RetornoDadosUsuarioDTO> atualizarUsuario(@PathVariable String idUsuario, @RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.atualizarApenasUsuario(idUsuario, usuarioDTO));
    }

}
