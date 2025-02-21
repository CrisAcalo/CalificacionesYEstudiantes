package espe.edu.ec.base_datos_calificaciones.controller;

import espe.edu.ec.base_datos_calificaciones.model.Nota;
import espe.edu.ec.base_datos_calificaciones.model.Estudiante;
import espe.edu.ec.base_datos_calificaciones.service.NotaService;
import espe.edu.ec.base_datos_calificaciones.service.EstudianteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/notas")
public class NotaWebController {

    private final NotaService notaService;
    private final EstudianteService estudianteService;

    public NotaWebController(NotaService notaService, EstudianteService estudianteService) {
        this.notaService = notaService;
        this.estudianteService = estudianteService;
    }

    @PostMapping("/guardar")
    public String guardarNota(@ModelAttribute Nota nota, @RequestParam Long estudianteId) {
        Estudiante estudiante = estudianteService.obtenerPorId(estudianteId)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));
        nota.setEstudiante(estudiante);
        notaService.guardar(nota);
        return "redirect:/estudiantes/editar/" + estudianteId;
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarNota(@RequestParam Long notaId, @RequestParam String asignatura, @RequestParam Double nota) {

        notaService.actualizar(notaId, asignatura, nota);
        return "redirect:/estudiantes/editar/" + notaId;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarNota(@PathVariable Long id) {
        Nota nota = notaService.obtenerPorId(id);
        Long estudianteId = nota.getEstudiante().getId();
        notaService.eliminar(id);
        return "redirect:/estudiantes/editar/" + estudianteId;
    }
}
