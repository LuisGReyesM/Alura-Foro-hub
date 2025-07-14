package com.alura.foro_hub.domain.usuario;

/**
 * DTO para registrar un nuevo usuario.
 * Contiene los datos necesarios para crear un usuario.
 */
public record DatosRegistroUsuario(
        String login,       // Nombre de usuario o login único
        String contrasena   // Contraseña en texto plano (se encriptará antes de guardar)
) {}
