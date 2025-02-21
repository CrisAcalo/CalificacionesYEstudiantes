import { Component, OnInit } from '@angular/core';
import { EstudianteService } from '../services/estudiante.service';
import { Estudiante } from '../models/Estudiante.model';
import { Router } from '@angular/router';
import { TableModule } from 'primeng/table';
import { FormsModule } from '@angular/forms';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { DatePickerModule } from 'primeng/datepicker';
import { FloatLabelModule } from 'primeng/floatlabel';

@Component({
  selector: 'app-estudiante-list',
  imports: [TableModule,FormsModule,CardModule,ButtonModule,InputTextModule,DatePickerModule,FloatLabelModule],
  templateUrl: './estudiante-list.component.html',
  styleUrl: './estudiante-list.component.css'
})
export class EstudianteListComponent implements OnInit {
  estudiantes: Estudiante[] = [];
  nuevoEstudiante: Estudiante = { nombre: '', apellido: '', email: '', fechaNacimiento: '' };


  constructor(private estudianteService: EstudianteService, private router: Router) { }

  ngOnInit(): void {
    this.obtenerEstudiantes();
  }

  obtenerEstudiantes(): void {
    this.estudianteService.obtenerEstudiantes().subscribe(
      (data) => {
        this.estudiantes = data;
      },
      (error) => {
        console.error('Error al obtener estudiantes', error);
      }
    );
  }

  agregarEstudiante(): void {
    this.estudianteService.agregarEstudiante(this.nuevoEstudiante).subscribe(() => {
      this.nuevoEstudiante = { nombre: '', apellido: '', email: '', fechaNacimiento: '' }; // Limpiar el formulario
      this.obtenerEstudiantes(); // Actualiza la lista de estudiantes
    });
  }

  editarEstudiante(id: number): void {
    this.router.navigate(['/estudiantes/editar', id]);
  }

  eliminarEstudiante(id: number): void {
    this.estudianteService.eliminarEstudiante(id).subscribe(
      () => {
        this.estudiantes = this.estudiantes.filter((estudiante) => estudiante.id !== id);
      },
      (error) => {
        console.error('Error al eliminar estudiante', error);
      }
    );
  }
}
