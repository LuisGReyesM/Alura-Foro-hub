package com.alura.foro_hub.infra.security;

/**
 * DTO para encapsular el token JWT generado.
 * Se utiliza para enviar el token en la respuesta HTTP tras autenticación.
 */
public record DatosTokenJWT(String token) {
}
