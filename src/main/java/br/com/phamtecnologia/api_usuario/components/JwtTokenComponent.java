package br.com.phamtecnologia.api_usuario.components;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenComponent {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiration;

    public Date getExpiration() {

        var dataAtual = new Date();
        return new Date(dataAtual.getTime() + Integer.parseInt(expiration));
    }

    public String getToken(UUID usuarioId, String email, String perfil) {

        return Jwts.builder()
                .setSubject(usuarioId.toString())
                .claim("email", email)
                .claim("perfil", perfil)
                .setIssuedAt(new Date())
                .setExpiration(getExpiration())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
