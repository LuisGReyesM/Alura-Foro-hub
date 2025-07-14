package com.alura.foro_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para la actualización de un tópico.
 * Contiene los datos que se pueden modificar en un tópico existente.
 */
public record DatosActualizacionTopico(
        @NotBlank // El título no puede estar vacío o ser nulo
        String titulo,

        @NotBlank // El mensaje no puede estar vacío o ser nulo
        String mensaje,

        @NotNull // El autor debe estar presente y no puede ser nulo
        Long autor,

        @NotNull // El curso debe estar presente y no puede ser nulo
        Long curso
) {}
