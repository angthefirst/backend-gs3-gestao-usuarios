package com.angleby.gs3.gestao.service.mapper;

import com.angleby.gs3.gestao.domain.dto.EnderecoDTO;
import com.angleby.gs3.gestao.domain.entity.Endereco;
import com.angleby.gs3.gestao.domain.entity.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EnderecoMapper implements MapperAbstrato<Endereco, EnderecoDTO> {

    @Override
    public EnderecoDTO entidadeParaDTO(Endereco entidade) {
        return EnderecoDTO.builder()
                .rua(entidade.getRua())
                .bairro(entidade.getBairro())
                .cidade(entidade.getCidade())
                .build();
    }

    @Override
    public Endereco dtoParaEntidade(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .usuario(null)
                .rua(enderecoDTO.rua())
                .bairro(enderecoDTO.bairro())
                .cidade(enderecoDTO.cidade())
                .build();
    }

    public Endereco dtoParaEntidade(EnderecoDTO enderecoDTO, Usuario usuario) {
        return Endereco.builder()
                .usuario(usuario)
                .rua(enderecoDTO.rua())
                .bairro(enderecoDTO.bairro())
                .cidade(enderecoDTO.cidade())
                .build();
    }
}
