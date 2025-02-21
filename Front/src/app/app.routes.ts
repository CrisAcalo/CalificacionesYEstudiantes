import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EstudianteListComponent } from './estudiante-list/estudiante-list.component';
import { EstudianteEditComponent } from './estudiante-edit/estudiante-edit.component';

export const routes: Routes = [
    { path: '', component: EstudianteListComponent },
    { path: 'estudiantes/editar/:id', component: EstudianteEditComponent }
];
