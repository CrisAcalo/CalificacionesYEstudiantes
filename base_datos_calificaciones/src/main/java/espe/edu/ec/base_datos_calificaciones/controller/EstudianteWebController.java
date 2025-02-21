package espe.edu.ec.base_datos_calificaciones.controller;

import espe.edu.ec.base_datos_calificaciones.model.Estudiante;
import espe.edu.ec.base_datos_calificaciones.service.EstudianteService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteWebController {
    private final EstudianteService estudianteService;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
    public EstudianteWebController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public String listarEstudiantes(Model model) {
        List<Estudiante> estudiantes = estudianteService.obtenerTodos();
        model.addAttribute("estudiantes", estudiantes);
        return "estudiante/index";  // Renderiza estudiante/index.html
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiante/crear";  // Renderiza estudiante/crear.html
    }

    @PostMapping("/guardar")
    public String guardarEstudiante(@ModelAttribute Estudiante estudiante) {
        estudianteService.guardar(estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/editar/{id}")
    public String editarEstudiante(@PathVariable Long id, Model model) {
        Estudiante estudiante = estudianteService.obtenerPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID no válido: " + id));
        model.addAttribute("estudiante", estudiante);
        return "estudiante/editar";  // Renderiza estudiante/editar.html
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarEstudiante(@PathVariable Long id, @ModelAttribute Estudiante estudiante) {
        estudianteService.actualizar(id, estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminar(id);
        return "redirect:/estudiantes";
    }
}
