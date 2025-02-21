package espe.edu.ec.base_datos_calificaciones.controller;

import espe.edu.ec.base_datos_calificaciones.model.Estudiante;
import espe.edu.ec.base_datos_calificaciones.service.EstudianteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EntidadController {

    private final EstudianteService estudianteService;

    public EntidadController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public List<Estudiante> obtenerTodos() {
        return estudianteService.obtenerTodos();
    }

    @GetMapping("/{id}")

    public ResponseEntity<Estudiante> obtenerPorId(@PathVariable Long id) {
        return estudianteService.obtenerPorId(id)
                .map(ResponseEntity::ok)  // Devuelve 200 OK si se encuentra
                .orElseGet(() -> ResponseEntity.notFound().build());  // Devuelve 404 si no existe
    }

    //
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizarEstudiante(@PathVariable Long id, @RequestBody Estudiante estudianteDetalles) {
        return estudianteService.actualizar(id, estudianteDetalles)
                .map(ResponseEntity::ok)  // Devuelve 200 OK si se actualiza correctamente
                .orElseGet(() -> ResponseEntity.notFound().build());  // Devuelve 404 si no existe
    }

    //
    @PostMapping
    public Estudiante agregarEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteService.guardar(estudiante);
    }

    @DeleteMapping("/{id}")
    public void eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminar(id);
    }
}

