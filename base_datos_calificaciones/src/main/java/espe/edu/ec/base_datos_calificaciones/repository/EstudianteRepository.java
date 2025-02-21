package espe.edu.ec.base_datos_calificaciones.repository;



import espe.edu.ec.base_datos_calificaciones.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

}

