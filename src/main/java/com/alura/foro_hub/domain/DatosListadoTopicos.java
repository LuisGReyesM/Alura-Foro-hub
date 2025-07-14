package com.alura.foro_hub.domain;


public record DatosListadoTopicos(
        Long id,
        String titulo,
        String mensaje,
        String status
) {
    public DatosListadoTopicos(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getStatus());
    }
}
