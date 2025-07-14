package com.alura.foro_hub.domain.usuario;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Entidad que representa un usuario en el sistema.
 * Implementa UserDetails para integración con Spring Security.
 */
@Entity(name = "Usuario")
@Table(name = "usuarios")
@EqualsAndHashCode(of = "id") // Equals y hashCode basados solo en el campo 'id'
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autogenerado
    private Long id;

    private String login;      // Nombre de usuario (login)

    private String contrasena; // Contraseña encriptada

    /**
     * Constructor sin argumentos requerido por JPA.
     */
    public Usuario() {
    }

    /**
     * Constructor con todos los campos para crear un objeto Usuario completo.
     */
    public Usuario(Long id, String login, String contrasena) {
        this.id = id;
        this.login = login;
        this.contrasena = contrasena;
    }

    // Getters y setters estándar

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // Métodos requeridos por UserDetails para Spring Security

    /**
     * Define los roles o permisos del usuario.
     * Por ahora, todos los usuarios tienen el rol "ROLE_USER".
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * Devuelve la contraseña encriptada del usuario.
     */
    @Override
    public String getPassword() {
        return contrasena;
    }

    /**
     * Devuelve el nombre de usuario para autenticación.
     */
    @Override
    public String getUsername() {
        return login;
    }

    /**
     * Indica si la cuenta no ha expirado.
     * Por simplicidad, siempre retorna true.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica si la cuenta no está bloqueada.
     * Por simplicidad, siempre retorna true.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica si las credenciales no han expirado.
     * Por simplicidad, siempre retorna true.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica si el usuario está habilitado.
     * Por simplicidad, siempre retorna true.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
