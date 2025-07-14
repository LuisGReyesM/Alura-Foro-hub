package com.alura.foro_hub.controller;

import com.alura.foro_hub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@RestController // Marca esta clase como un controlador REST
@RequestMapping("/topicos") // Define el endpoint base para todos los métodos de este controlador
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository; // Repositorio para acceso a datos de tópicos

    /**
     * Endpoint POST para registrar un nuevo tópico
     * @param datos Información del tópico a registrar (título, mensaje, autor, curso)
     * @return Respuesta HTTP con el tópico creado o error si hay duplicados
     */
    @PostMapping
    public ResponseEntity<?> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos) {
        // Verificar si ya existe un tópico con mismo título y mensaje para evitar duplicados
        if (topicoRepository.findByTituloAndMensaje(datos.titulo(), datos.mensaje()).isPresent()) {
            return ResponseEntity.badRequest().body("Ya existe un tópico con el mismo título y mensaje.");
        }

        // Crear un nuevo objeto Topico con los datos recibidos y fecha actual, estado por defecto "ABIERTO"
        Topico topico = new Topico(
                null,
                datos.titulo(),
                datos.mensaje(),
                LocalDateTime.now(),
                "ABIERTO", // Estado inicial del tópico
                datos.autor(),
                datos.curso()
        );
        topicoRepository.save(topico); // Guardar el tópico en la base de datos
        return ResponseEntity.ok(topico); // Devolver el tópico creado
    }

    /**
     * Endpoint GET para listar tópicos paginados
     * @param paginacion Parámetros de paginación y ordenamiento
     * @return Página con listado de tópicos en formato DTO
     */
    @GetMapping
    public ResponseEntity<Page<DatosListadoTopicos>> listar(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacion) {
        // Obtener todos los tópicos paginados y mapearlos a DTO para la respuesta
        var page = topicoRepository.findAll(paginacion).map(DatosListadoTopicos::new);
        return ResponseEntity.ok(page);
    }

    /**
     * Endpoint GET para obtener detalle de un tópico por su ID
     * @param id Identificador del tópico
     * @return Detalle del tópico o 404 si no se encuentra
     */
    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> detalle(@PathVariable Long id) {
        var topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(new DatosDetalleTopico(topico.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint PUT para actualizar un tópico existente
     * @param id ID del tópico a actualizar
     * @param datos Nuevos datos para actualizar el tópico
     * @return 200 OK si se actualiza o error si no existe o hay duplicados
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datos) {
        var topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // Si no existe el tópico, retorna 404
        }

        // Verificar si otro tópico tiene el mismo título y mensaje para evitar duplicados
        var duplicado = topicoRepository.findByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if (duplicado.isPresent() && !duplicado.get().getId().equals(id)) {
            return ResponseEntity.badRequest().body("Ya existe un tópico con el mismo título y mensaje.");
        }

        // Actualizar los campos del tópico con los datos recibidos
        var topico = topicoOptional.get();
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setAutor(datos.autor());
        topico.setCurso(datos.curso());
        // Puedes agregar actualización de más campos si es necesario

        topicoRepository.save(topico); // Guardar cambios en la base de datos
        return ResponseEntity.ok().build(); // Respuesta 200 OK sin contenido
    }

    /**
     * Endpoint DELETE para eliminar un tópico por ID
     * @param id ID del tópico a eliminar
     * @return 204 No Content si elimina, 404 si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        var topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 si no existe
        }
        topicoRepository.deleteById(id); // Eliminar tópico
        return ResponseEntity.noContent().build(); // 204 No Content, eliminación exitosa
    }

}
