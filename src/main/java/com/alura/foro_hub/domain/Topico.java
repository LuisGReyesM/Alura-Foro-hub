package com.alura.foro_hub.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Topico")
@Table(name = "topicos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private String status;
    private Long autor;  // Puedes dejarlo como Long si no tienes la entidad Usuario
    private Long curso;  // Puedes dejarlo como Long si no tienes la entidad Curso

    public Topico() {
    }

    public Topico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, String status, Long autor, Long curso) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.status = status;
        this.autor = autor;
        this.curso = curso;
    }

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