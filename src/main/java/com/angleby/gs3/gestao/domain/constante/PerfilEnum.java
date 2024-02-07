package com.angleby.gs3.gestao.domain.constante;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PerfilEnum {
    ADMIN(Permissao.ADMIN, 1L),
    COMUM(Permissao.COMUM, 2L);

    private final String descricao;
    private final Long id;
}
