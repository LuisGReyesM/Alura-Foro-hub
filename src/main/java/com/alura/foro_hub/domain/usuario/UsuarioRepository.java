package com.alura.foro_hub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

// Interfaz para acceder a la base de datos de usuarios.
// Extiende JpaRepository para heredar métodos CRUD básicos.
// Aquí se define un método para buscar un usuario por su login (nombre de usuario).
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método para encontrar un usuario por su login.
    // Retorna un objeto UserDetails que Spring Security usa para autenticación.
    // Si no encuentra el usuario, retornará null, lo cual puede generar problemas si no se maneja bien.
    Optional<Usuario> findByLogin(String login);



}
