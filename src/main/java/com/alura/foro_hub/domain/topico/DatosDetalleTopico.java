package com.alura.foro_hub.domain.topico;

import java.time.LocalDateTime;

/**
 * DTO para detallar la información completa de un tópico.
 * Este record representa los datos que se mostrarán al consultar un tópico específico.
 */
public record DatosDetalleTopico(
        Long id,                 // Identificador único del tópico
        String titulo,           // Título del tópico
        String mensaje,          // Mensaje o contenido del tópico
        LocalDateTime fechaCreacion, // Fecha y hora en que fue creado el tópico
        String status,           // Estado o status actual del tópico (ej: ABIERTO, CERRADO)
        Long autor,              // Identificador del autor del tópico
        Long curso               // Identificador del curso asociado al tópico
) {
    /**
     * Constructor que recibe una entidad Topico y mapea sus datos al DTO
     * @param topico Entidad Topico de la cual se extraen los datos
     */
    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso()
        );
    }
}
