package com.alura.foro_hub.controller;

// Importaciones necesarias para validación, seguridad, y respuesta HTTP
import com.alura.foro_hub.domain.usuario.DatosAutenticacion;
import com.alura.foro_hub.domain.usuario.Usuario;
import com.alura.foro_hub.infra.security.DatosTokenJWT;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alura.foro_hub.infra.security.TokenService;

/**
 * Controlador que expone el endpoint de autenticación (/login).
 * Se encarga de recibir credenciales, autenticar al usuario y devolver un token JWT si son válidas.
 */
@RestController // Marca la clase como controlador REST
@RequestMapping("/login") // Todas las rutas de esta clase comienzan con /login
public class AutenticacionController {

    @Autowired
    private TokenService tokenService; // Servicio que genera el token JWT

    @Autowired
    private AuthenticationManager manager; // Componente de Spring Security que autentica las credenciales

    /**
     * Endpoint POST que permite iniciar sesión
     * @param datos contiene el login y la contraseña del usuario
     * @return respuesta HTTP con el token JWT si las credenciales son correctas
     */
    @PostMapping // Maneja solicitudes POST a /login
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos) {
        // 1. Se crea el token de autenticación a partir de las credenciales recibidas
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                datos.login(), datos.contrasena());

        // 2. Se autentica el usuario (verifica en la base de datos)
        var autenticacion = manager.authenticate(authenticationToken);

        // 3. Si es válido, se genera el token JWT
        var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

        // 4. Se retorna el token en el cuerpo de la respuesta con código 200 OK
        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }
}
