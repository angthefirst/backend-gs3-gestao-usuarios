package com.angleby.gs3.gestao.domain.dto;

import lombok.Builder;

@Builder
public record UsuarioDTO(String id, String login, String senha, String nomeCompleto, String email, PerfilDTO perfil, EnderecoDTO endereco) {
}
