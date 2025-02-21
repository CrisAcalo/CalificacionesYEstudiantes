import { Estudiante } from './Estudiante.model';

export interface Nota {
  id?: number;
  asignatura: string;
  nota: number;
  fechaRegistro?: string; // Formato 'YYYY-MM-DD'
  estudiante?: Estudiante;
  estudiante_id?: number;
}
