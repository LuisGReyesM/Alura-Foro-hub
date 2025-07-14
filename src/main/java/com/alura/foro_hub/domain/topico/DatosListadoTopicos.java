package com.alura.foro_hub.domain.topico;

/**
 * DTO para listar tópicos con información básica.
 * Se utiliza para mostrar listados donde no es necesario incluir todos los detalles.
 */
public record DatosListadoTopicos(
        Long id,         // Identificador único del tópico
        String titulo,   // Título del tópico
        String mensaje,  // Mensaje o contenido breve del tópico
        String status    // Estado o status actual del tópico (ej: ABIERTO, CERRADO)
) {
    /**
     * Constructor que recibe una entidad Topico y extrae los datos necesarios para el listado
     * @param topico Entidad Topico desde la cual se obtienen los datos
     */
    public DatosListadoTopicos(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getStatus());
    }
}
