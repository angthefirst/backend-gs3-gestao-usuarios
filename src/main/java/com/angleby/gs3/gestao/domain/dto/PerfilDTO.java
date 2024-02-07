package com.angleby.gs3.gestao.domain.dto;

import lombok.Builder;

@Builder
public record PerfilDTO(Long id, String descricao) {
}
