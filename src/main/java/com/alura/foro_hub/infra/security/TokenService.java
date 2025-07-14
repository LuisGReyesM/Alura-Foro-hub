package com.alura.foro_hub.infra.security;

import com.alura.foro_hub.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
@Service
public class TokenService {

    // Inyecta el secreto para firmar y verificar los tokens desde el archivo de configuración
    @Value("${api.secutiry.token.secret}")
    private String secret;

    // Método para generar un token JWT firmado con la información del usuario
    public String generarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Foro_hub")                // Emisor del token
                    .withSubject(usuario.getLogin())           // Identificador principal (subject) es el login del usuario
                    .withExpiresAt(fechaExpiracion())          // Fecha de expiración del token
                    .sign(algoritmo);                          // Firma el token con el algoritmo y secreto
        } catch (JWTCreationException exception) {
            // Captura errores en la creación del token y lanza excepción en runtime
            throw new RuntimeException("error al generar el token JWT", exception);
        }
    }

    // Define la fecha de expiración para el token (12 horas a partir del momento actual, zona horaria -04:00)
    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-04:00"));
    }

    // Método para validar un token JWT y extraer el subject (login del usuario)
    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API Foro_hub")        // Verifica el emisor esperado
                    .build()
                    .verify(tokenJWT)                  // Valida el token y su firma
                    .getSubject();                    // Extrae el subject del token
        } catch (JWTVerificationException exception) {
            // En caso de token inválido o expirado, lanza excepción en runtime
            throw new RuntimeException("Token JWT invalido o expirado!");
        }
    }

}
