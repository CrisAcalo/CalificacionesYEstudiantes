package espe.edu.ec.base_datos_calificaciones.service;


import espe.edu.ec.base_datos_calificaciones.model.Nota;
import espe.edu.ec.base_datos_calificaciones.repository.NotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaService {

    private final NotaRepository notaRepository;

    public NotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    public Nota obtenerPorId(Long id) {
        return notaRepository.findById(id).orElse(null);
    }

    public Nota actualizar(Long id, Nota notaDetalles) {
        return notaRepository.findById(id).map(nota -> {
            nota.setAsignatura(notaDetalles.getAsignatura());
            nota.setNota(notaDetalles.getNota());
            return notaRepository.save(nota);
        }).orElse(null);
    }
    public boolean actualizar(Long id, String materia, Double nota) {
        Nota notaExistente = notaRepository.findById(id).orElse(null);
        if (notaExistente == null) {
            return false;
        }
        notaExistente.setAsignatura(materia);
        notaExistente.setNota(nota);
        notaRepository.save(notaExistente);
        return true;
    }

    public List<Nota> obtenerTodas() {
        return notaRepository.findAll();
    }

    public Nota guardar(Nota nota) {
        return notaRepository.save(nota);
    }

    public void eliminar(Long id) {
        notaRepository.deleteById(id);
    }
}
