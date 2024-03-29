package com.angleby.gs3.gestao.config;

import com.angleby.gs3.gestao.domain.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String tokenSecret;

    public String gerarToken(Usuario usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            String token = JWT.create()
                    .withIssuer("gs3-gestao-usuarios")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(gerarTempoDeExpiracaoDoToken())
                    .withClaim("perfis", getPerfis(usuario))
                    .withClaim("idUsuario", usuario.getId())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Falha na geração de token do usuário.", exception);
        }
    }

    public String validarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenSecret);
            return JWT.require(algorithm)
                    .withIssuer("gs3-gestao-usuarios")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }

    private List<String> getPerfis(Usuario usuario) {
        Collection<? extends GrantedAuthority> authorities = usuario.getAuthorities();
        List<String> perfis = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            perfis.add(authority.getAuthority());
        }
        return perfis;
    }

    private Instant gerarTempoDeExpiracaoDoToken() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }
}
