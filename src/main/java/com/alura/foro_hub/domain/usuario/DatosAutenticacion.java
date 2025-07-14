package com.alura.foro_hub.domain.usuario;

/**
 * Representa los datos de autenticación que el usuario proporciona
 * para iniciar sesión en el sistema.
 *
 * @param login nombre de usuario o login
 * @param contrasena contraseña del usuario
 */
public record DatosAutenticacion(String login, String contrasena) {
}
