package com.angleby.gs3.gestao.domain.dto;

import lombok.Builder;

@Builder
public record RetornoDadosUsuarioDTO(String login, String nomeCompleto, String email, PerfilDTO perfil, RetornoEnderecoDTO endereco) {
}
