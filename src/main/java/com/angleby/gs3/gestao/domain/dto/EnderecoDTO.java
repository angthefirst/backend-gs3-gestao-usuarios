package com.angleby.gs3.gestao.domain.dto;

import lombok.Builder;

@Builder
public record EnderecoDTO(UsuarioDTO usuarioDTO, String rua, String bairro, String cidade) {
}
