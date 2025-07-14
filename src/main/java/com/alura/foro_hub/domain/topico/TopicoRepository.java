package com.alura.foro_hub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad Topico.
 * Proporciona métodos CRUD y consultas personalizadas para tópicos.
 */
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    /**
     * Busca un tópico por título y mensaje exactos.
     * Se usa para evitar duplicados al crear o actualizar tópicos.
     *
     * @param titulo El título del tópico a buscar
     * @param mensaje El mensaje del tópico a buscar
     * @return Optional con el tópico si existe, o vacío si no
     */
    Optional<Topico> findByTituloAndMensaje(String titulo, String mensaje);
}
