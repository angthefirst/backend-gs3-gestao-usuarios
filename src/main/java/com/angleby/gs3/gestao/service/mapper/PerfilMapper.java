package com.angleby.gs3.gestao.service.mapper;

import com.angleby.gs3.gestao.domain.dto.PerfilDTO;
import com.angleby.gs3.gestao.domain.entity.Perfil;
import org.springframework.stereotype.Component;

@Component
public class PerfilMapper implements MapperAbstrato<Perfil, PerfilDTO> {

    @Override
    public PerfilDTO entidadeParaDTO(Perfil entidade) {
        return PerfilDTO.builder()
                .id(entidade.getId())
                .descricao(entidade.getDescricao())
                .build();
    }

    @Override
    public Perfil dtoParaEntidade(PerfilDTO perfilDTO) {
        return Perfil.builder()
                .id(perfilDTO.id())
                .descricao(perfilDTO.descricao())
                .build();
    }
}
