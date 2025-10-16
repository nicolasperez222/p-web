// src/app/resolucion/resolucion-list.component.ts
import { Component, OnInit, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Resolucion } from '../model/resolucion';
import { ResolucionService } from '../shared/resolucion.service';
import { ResolucionFormComponent } from '../resolucion-form/resolucion-form';


@Component({
  selector: 'app-resolucion-list',
  standalone: true,
  imports: [CommonModule, ResolucionFormComponent],
  templateUrl: './resolucion-list.html',                                                                                                                                                                                                                                                                                  
  styleUrls: ['./resolucion-list.css']
})
export class ResolucionListComponent implements OnInit {
  private service = inject(ResolucionService);

  resoluciones = signal<Resolucion[]>([]);
  resolucionSeleccionada: Resolucion | null | undefined = undefined;

  mostrarFormulario = false;

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.service.getResoluciones().subscribe({ next: r => this.resoluciones.set(r || []), error: e => console.error(e) });
  }

  nuevo() {
    this.resolucionSeleccionada = null; // nuevo
    this.mostrarFormulario = true;
  }

  editar(r: Resolucion) {
    this.resolucionSeleccionada = { ...r };
    this.mostrarFormulario = true;
  }

  eliminar(id?: number) {
    if (!id) return;
    if (!confirm('¿Eliminar resolución?')) return;
    this.service.deleteResolucion(id).subscribe({ next: () => this.load(), error: e => console.error(e) });
  }

  onSaved() {
    this.mostrarFormulario = false;
    this.resolucionSeleccionada = undefined;
    this.load();
  }

  onCancel() {
    this.mostrarFormulario = false;
    this.resolucionSeleccionada = undefined;
  }

  
}
