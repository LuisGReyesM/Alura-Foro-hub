package com.alura.foro_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para el registro (creación) de un nuevo tópico.
 * Contiene los datos necesarios para crear un tópico.
 */
public record DatosRegistroTopico(
        @NotBlank // El título no puede estar vacío ni ser nulo
        String titulo,

        @NotBlank // El mensaje no puede estar vacío ni ser nulo
        String mensaje,

        @NotNull // El autor debe estar presente y no ser nulo (ID del usuario)
        Long autor,

        @NotNull // El curso debe estar presente y no ser nulo (ID del curso)
        Long curso
) {}
