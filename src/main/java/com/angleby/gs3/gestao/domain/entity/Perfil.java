package com.angleby.gs3.gestao.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "PERFIL")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil {

    public static final String ADMIN = "ADMIN";
    public static final String COMUM = "COMUM";

    @Id
    @Column(name = "ID_PERFIL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRICAO")
    private String descricao;
}
