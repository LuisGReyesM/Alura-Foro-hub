package com.alura.foro_hub.infra.security;


import com.alura.foro_hub.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    // Repositorio para acceder a la información del usuario en la base de datos
    @Autowired
    private UsuarioRepository repository;

    // Servicio para manejar la lógica de validación y extracción de datos del token JWT
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Recupera el token JWT desde el encabezado Authorization de la solicitud HTTP
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            // Extrae el 'subject' (usuario/login) del token JWT usando TokenService
            var subject = tokenService.getSubject(tokenJWT);

            // Busca el usuario en la base de datos según el login extraído
            var usuario = repository.findByLogin(subject);

            // Crea un objeto de autenticación con el usuario y sus roles/autoridades
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                    usuario.get().getAuthorities());

            // Establece la autenticación en el contexto de seguridad de Spring Security
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continúa la cadena de filtros para que la solicitud siga su procesamiento normal
        filterChain.doFilter(request, response);
    }

    // Método auxiliar que obtiene el token JWT del encabezado "Authorization" y elimina el prefijo "Bearer "
    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
