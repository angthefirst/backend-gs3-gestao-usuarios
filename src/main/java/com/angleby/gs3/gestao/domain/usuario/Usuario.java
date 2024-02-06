package com.angleby.gs3.gestao.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Table(name = "USUARIO")
@Entity(name = "USUARIO")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nomeDeUsuario;
    private String senha;
    private Perfil perfil;

    public Usuario(String nomeDeUsuario, String senha, Perfil perfil){
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.perfil = perfil;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.perfil == Perfil.ADMIN) return List.of(new SimpleGrantedAuthority("PERFIL_ADMIN"));
        if(this.perfil == Perfil.COMUM) return List.of(new SimpleGrantedAuthority("PERFIL_COMUM"));
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return nomeDeUsuario;
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
