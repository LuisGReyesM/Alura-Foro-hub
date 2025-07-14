package com.alura.foro_hub.controller;

import com.alura.foro_hub.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import com.alura.foro_hub.domain.usuario.UsuarioRepository;
import com.alura.foro_hub.domain.usuario.DatosRegistroUsuario;

@RestController // Marca la clase como controlador REST
@RequestMapping("/usuarios") // Define el endpoint base /usuarios para este controlador
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository; // Repositorio para acceder a la tabla usuarios

    @Autowired
    private PasswordEncoder passwordEncoder; // Servicio para encriptar contraseñas

    /**
     * Endpoint POST para registrar un nuevo usuario
     * @param datos Datos necesarios para crear un usuario (login y contraseña)
     * @return Respuesta HTTP 200 OK si se registra correctamente o 400 si login ya existe
     */
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody @Valid DatosRegistroUsuario datos) {

        // Validar que no exista un usuario con el mismo login
        if (usuarioRepository.findByLogin(datos.login()).isPresent()) {
            return ResponseEntity.badRequest().body("Login ya registrado");
        }

        // Encriptar la contraseña antes de guardar
        String contrasenaEncriptada = passwordEncoder.encode(datos.contrasena());

        // Crear instancia Usuario con datos recibidos y contraseña encriptada
        Usuario usuario = new Usuario(null, datos.login(), contrasenaEncriptada);

        // Guardar usuario en la base de datos
        usuarioRepository.save(usuario);

        // Retornar respuesta 200 OK sin contenido
        return ResponseEntity.ok().build();
    }
}
