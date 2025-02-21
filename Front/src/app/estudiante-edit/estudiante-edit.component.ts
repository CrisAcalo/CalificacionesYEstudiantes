import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EstudianteService } from '../services/estudiante.service';
import { NotaService } from '../services/nota.service';
import { Estudiante } from '../models/Estudiante.model';
import { Nota } from '../models/Nota.model';
import { TableModule } from 'primeng/table';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { DatePickerModule } from 'primeng/datepicker';
import { FloatLabelModule } from 'primeng/floatlabel';

@Component({
  selector: 'app-estudiante-edit',
  imports: [TableModule, CommonModule, FormsModule, CardModule, ButtonModule, InputTextModule, DatePickerModule, FloatLabelModule],
  templateUrl: './estudiante-edit.component.html',
  styleUrls: ['./estudiante-edit.component.css']
})

export class EstudianteEditComponent implements OnInit {
  estudiante!: Estudiante;
  nuevaNota: Nota = { asignatura: '', nota: 0, fechaRegistro: '' };
  notas: Nota[] = [];
  estudianteId!: number;
  isEditing: boolean = false; // Nuevo estado para saber si estamos en modo edición

  constructor(
    private estudianteService: EstudianteService,
    private notaService: NotaService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.estudianteId = Number(this.route.snapshot.paramMap.get('id'));
    this.obtenerEstudiante();
    this.obtenerNotas();
  }

  obtenerEstudiante(): void {
    this.estudianteService.obtenerEstudiantePorId(this.estudianteId).subscribe(estudiante => {
      this.estudiante = estudiante;
    });
  }

  obtenerNotas(): void {
    this.notaService.obtenerNotas().subscribe(notas => {
      this.notas = notas.filter(nota => nota.estudiante_id === this.estudianteId);
    });
  }

  guardarEstudiante(): void {
    this.estudianteService.actualizarEstudiante(this.estudianteId, this.estudiante).subscribe(() => {
      this.router.navigate(['/']);
    });
  }

  agregarNota(): void {
    const nuevaNotaData = { ...this.nuevaNota, idEstudiante: this.estudianteId };
    this.notaService.agregarNota(nuevaNotaData).subscribe(() => {
      this.nuevaNota = { asignatura: '', nota: 0, fechaRegistro: '' }; // Limpiar formulario
      this.obtenerNotas(); // Actualizar lista de notas
    });
  }

  editarNota(nota: Nota): void {
    this.nuevaNota = { ...nota }; // Cargar la nota en el formulario
    this.nuevaNota.id = nota.id; // Conservar el ID de la nota
    this.isEditing = true; // Activar modo edición
  }

  guardarNota(): void {
    if (this.nuevaNota.id) {
      this.notaService.actualizarNota(this.nuevaNota.id, this.nuevaNota).subscribe(() => {
        this.nuevaNota = { asignatura: '', nota: 0, fechaRegistro: '' }; // Limpiar formulario
        this.obtenerNotas(); // Actualizar lista de notas
        this.isEditing = false; // Desactivar modo edición
      });
    } else {
      this.agregarNota();
    }
  }

  cancelarEdicion(): void {
    this.nuevaNota = { asignatura: '', nota: 0, fechaRegistro: '' }; // Limpiar formulario
    this.isEditing = false; // Desactivar modo edición
  }

  eliminarNota(id: number): void {
    this.notaService.eliminarNota(id).subscribe(() => {
      this.obtenerNotas(); // Actualiza la lista después de eliminar
    });
  }
}
