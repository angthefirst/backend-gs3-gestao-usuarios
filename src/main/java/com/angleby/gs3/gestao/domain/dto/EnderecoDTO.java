package com.angleby.gs3.gestao.domain.dto;

import lombok.Builder;

@Builder
public record EnderecoDTO(UsuarioDTO usuarioDTO, String rua, String bairro, String cidade) {
    public EnderecoDTO(String rua, String bairro, String cidade) {
        this(null, rua, bairro, cidade);
    }

    public EnderecoDTO(UsuarioDTO usuarioDTO, String rua, String bairro, String cidade) {
        this.usuarioDTO = usuarioDTO;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
    }
}
