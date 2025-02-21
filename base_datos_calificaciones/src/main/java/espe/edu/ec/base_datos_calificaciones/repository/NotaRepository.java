package espe.edu.ec.base_datos_calificaciones.repository;



import espe.edu.ec.base_datos_calificaciones.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Long> {

}
