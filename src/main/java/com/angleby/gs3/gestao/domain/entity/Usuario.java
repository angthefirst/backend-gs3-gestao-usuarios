package com.angleby.gs3.gestao.domain.entity;

import com.angleby.gs3.gestao.domain.constante.PerfilEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Table(name = "USUARIO")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "NOME_COMPLETO")
    private String nomeCompleto;

    @Column(name = "EMAIL")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERFIL")
    private Perfil perfil;

    public Usuario(String login, String senha, String nomeCompleto, String email, Perfil perfil) {
        this.login = login;
        this.senha = senha;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.perfil = perfil;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(Objects.equals(this.perfil.getDescricao(), PerfilEnum.ADMIN.getDescricao())) return List.of(new SimpleGrantedAuthority(PerfilEnum.ADMIN.getDescricao()));
        if(Objects.equals(this.perfil.getDescricao(), PerfilEnum.COMUM.getDescricao())) return List.of(new SimpleGrantedAuthority(PerfilEnum.COMUM.getDescricao()));
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
