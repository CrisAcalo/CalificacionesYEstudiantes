package espe.edu.ec.base_datos_calificaciones.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Entity
@Table(name = "notas")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String asignatura;
    private Double nota;

    @Temporal(TemporalType.DATE)
    private Date fechaRegistro = new Date();

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    @JsonIgnore // Ignorar la serialización de Estudiante en este contexto
    private Estudiante estudiante;

    public Nota() {}

    public Nota(String asignatura, Double nota, Estudiante estudiante) {
        this.asignatura = asignatura;
        this.nota = nota;
        this.estudiante = estudiante;
    }

    public String getCalificacion() {
        if (nota >= 9) return "Sobresaliente";
        if (nota >= 7) return "Notable";
        if (nota >= 5) return "Bien";
        return "Suspenso";
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Long getEstudianteId() {
        return estudiante != null ? estudiante.getId() : null; // Agregado para retornar el ID del estudiante
    }
}
