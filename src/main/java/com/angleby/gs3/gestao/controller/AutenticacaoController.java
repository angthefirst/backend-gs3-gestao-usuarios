package com.angleby.gs3.gestao.controller;

import com.angleby.gs3.gestao.domain.dto.AutenticacaoDTO;
import com.angleby.gs3.gestao.domain.dto.RetornoLoginDTO;
import com.angleby.gs3.gestao.domain.dto.UsuarioDTO;
import com.angleby.gs3.gestao.service.AutenticacaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("auth")
public class AutenticacaoController {

    private AutenticacaoService autenticacaoService;

    @PostMapping("/login")
    public ResponseEntity<RetornoLoginDTO> login(@RequestBody AutenticacaoDTO autenticacaoDTO) {
        return ResponseEntity.ok(autenticacaoService.realizarLogin(autenticacaoDTO));
    }

    @PostMapping("/registro")
    public ResponseEntity registrar(@RequestBody UsuarioDTO usuarioDTO){
        autenticacaoService.realizarRegistro(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
