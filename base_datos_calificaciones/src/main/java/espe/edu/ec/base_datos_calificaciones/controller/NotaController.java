package espe.edu.ec.base_datos_calificaciones.controller;

import espe.edu.ec.base_datos_calificaciones.model.Estudiante;
import espe.edu.ec.base_datos_calificaciones.model.Nota;
import espe.edu.ec.base_datos_calificaciones.service.EstudianteService;
import espe.edu.ec.base_datos_calificaciones.service.NotaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    private final NotaService notaService;
    private final EstudianteService estudianteService;

    public NotaController(NotaService notaService, EstudianteService estudianteService) {
        this.notaService = notaService;
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public List<NotaDTO> obtenerTodas() {
        List<Nota> notas = notaService.obtenerTodas();
        return notas.stream()
                .map(nota -> new NotaDTO(nota.getId(), nota.getAsignatura(), nota.getNota(),
                        nota.getFechaRegistro().toString(), nota.getEstudianteId()))
                .toList();
    }
    @GetMapping("/{id}")
    public Nota obtenerPorId(@PathVariable Long id) {
        return notaService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Nota actualizarNota(@PathVariable Long id, @RequestBody Nota notaDetalles) {
        return notaService.actualizar(id, notaDetalles);
    }

    @PostMapping
    public Nota agregarNota(@RequestBody NotaRequest nota) {
        //Encotrar estudiante

        Estudiante estudiante = estudianteService.obtenerPorId(nota.getIdEstudiante()).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Nota nuevaNota = new Nota();
        nuevaNota.setAsignatura(nota.getAsignatura());
        nuevaNota.setNota(nota.getNota());
        nuevaNota.setEstudiante(estudiante);

        return notaService.guardar(nuevaNota);
    }

    @DeleteMapping("/{id}")
    public void eliminarNota(@PathVariable Long id) {
        notaService.eliminar(id);
    }

    //DTO para request
    public static class NotaRequest {
        private String asignatura;
        private Double nota;
        private Long idEstudiante;

        public String getAsignatura() {
            return asignatura;
        }

        public void setAsignatura(String asignatura) {
            this.asignatura = asignatura;
        }

        public Double getNota() {
            return nota;
        }

        public void setNota(Double nota) {
            this.nota = nota;
        }

        public Long getIdEstudiante() {
            return idEstudiante;
        }

        public void setIdEstudiante(Long idEstudiante) {
            this.idEstudiante = idEstudiante;
        }
    }
    public class NotaDTO {
        private Long id;
        private String asignatura;
        private Double nota;
        private String fechaRegistro;
        private Long estudiante_id; // Añadir estudianteId

        public NotaDTO(Long id, String asignatura, Double nota, String fechaRegistro, Long estudiante_id) {
            this.id = id;
            this.asignatura = asignatura;
            this.nota = nota;
            this.fechaRegistro = fechaRegistro;
            this.estudiante_id = estudiante_id;
        }

        // Getters y Setters

        public Long getEstudiante_id() {
            return estudiante_id;
        }

        public void setEstudiante_id(Long estudiante_id) {
            this.estudiante_id = estudiante_id;
        }

        public String getFechaRegistro() {
            return fechaRegistro;
        }

        public void setFechaRegistro(String fechaRegistro) {
            this.fechaRegistro = fechaRegistro;
        }

        public Double getNota() {
            return nota;
        }

        public void setNota(Double nota) {
            this.nota = nota;
        }

        public String getAsignatura() {
            return asignatura;
        }

        public void setAsignatura(String asignatura) {
            this.asignatura = asignatura;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

}
