package com.alura.foro_hub.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull Long autor,
        @NotNull Long curso
) {}