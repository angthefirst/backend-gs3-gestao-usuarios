package com.angleby.gs3.gestao.repository;

import com.angleby.gs3.gestao.domain.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, String> {
}
