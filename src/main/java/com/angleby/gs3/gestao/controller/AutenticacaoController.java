package com.angleby.gs3.gestao.controller;

import com.angleby.gs3.gestao.domain.dto.AutenticacaoDTO;
import com.angleby.gs3.gestao.domain.dto.RetornoLoginDTO;
import com.angleby.gs3.gestao.domain.dto.UsuarioDTO;
import com.angleby.gs3.gestao.service.AutenticacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AutenticacaoController", description = "Operações relacionadas a autenticação")
@AllArgsConstructor
@RestController
@RequestMapping("auth")
public class AutenticacaoController {

    private AutenticacaoService autenticacaoService;

    @Operation(summary = "Realizar login", description = "Realiza o login do usuário")
    @PostMapping("/login")
    public ResponseEntity<RetornoLoginDTO> login(@RequestBody AutenticacaoDTO autenticacaoDTO) {
        return ResponseEntity.ok(autenticacaoService.realizarLogin(autenticacaoDTO));
    }

    @Operation(summary = "Realizar registro", description = "Realiza o registro do usuário")
    @PostMapping("/registro")
    public ResponseEntity registrar(@RequestBody UsuarioDTO usuarioDTO){
        autenticacaoService.realizarRegistro(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
