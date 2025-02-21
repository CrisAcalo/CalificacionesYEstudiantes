import { Nota } from './Nota.model';

export interface Estudiante {
  id?: number;
  nombre: string;
  apellido: string;
  email: string;
  fechaNacimiento: string; // Formato 'YYYY-MM-DD'
  notas?: Nota[];
}
