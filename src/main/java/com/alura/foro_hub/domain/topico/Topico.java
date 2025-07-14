package com.alura.foro_hub.domain.topico;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entidad que representa un tópico en el foro.
 * Mapeada a la tabla "topicos" en la base de datos.
 */
@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@Setter
@EqualsAndHashCode(of = "id") // Equals y hashCode basados solo en el campo 'id'
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Clave primaria autogenerada
    private Long id;

    private String titulo;    // Título del tópico
    private String mensaje;   // Mensaje o contenido del tópico

    @Column(name = "fecha_creacion") // Nombre personalizado de la columna
    private LocalDateTime fechaCreacion; // Fecha y hora de creación del tópico

    private String status;    // Estado del tópico (ej: ABIERTO, CERRADO)

    // Referencia al autor por su ID (se puede cambiar a entidad Usuario si está implementada)
    private Long autor;

    // Referencia al curso por su ID (se puede cambiar a entidad Curso si está implementada)
    private Long curso;

    /**
     * Constructor vacío necesario para JPA.
     */
    public Topico() {
    }

    /**
     * Constructor con todos los campos para crear un objeto Topico completo.
     */
    public Topico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, String status, Long autor, Long curso) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.status = status;
        this.autor = autor;
        this.curso = curso;
    }

    // Métodos getter y setter generados por Lombok (puedes omitirlos si usas @Getter/@Setter)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAutor() {
        return autor;
    }

    public void setAutor(Long autor) {
        this.autor = autor;
    }

    public Long getCurso() {
        return curso;
    }

    public void setCurso(Long curso) {
        this.curso = curso;
    }
}
