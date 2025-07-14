package com.alura.foro_hub.controller;


import com.alura.foro_hub.domain.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<?> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos) {
        // Verificar duplicados
        if (topicoRepository.findByTituloAndMensaje(datos.titulo(), datos.mensaje()).isPresent()) {
            return ResponseEntity.badRequest().body("Ya existe un tópico con el mismo título y mensaje.");
        }

        Topico topico = new Topico(
                null,
                datos.titulo(),
                datos.mensaje(),
                LocalDateTime.now(),
                "ABIERTO", // o el status que prefieras por defecto
                datos.autor(),
                datos.curso()
        );
        topicoRepository.save(topico);
        return ResponseEntity.ok(topico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopicos>> listar(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacion) {
        var page = topicoRepository.findAll(paginacion).map(DatosListadoTopicos::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> detalle(@PathVariable Long id) {
        var topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(new DatosDetalleTopico(topico.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datos) {
        var topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Verificar duplicados (título y mensaje)
        var duplicado = topicoRepository.findByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if (duplicado.isPresent() && !duplicado.get().getId().equals(id)) {
            return ResponseEntity.badRequest().body("Ya existe un tópico con el mismo título y mensaje.");
        }

        var topico = topicoOptional.get();
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setAutor(datos.autor());
        topico.setCurso(datos.curso());
        // Puedes actualizar otros campos si lo deseas

        topicoRepository.save(topico);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        var topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }


}