// src/app/resolucion/resolucion-form.component.ts
import { Component, OnInit, inject, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Resolucion } from '../model/resolucion';
import { ResolucionService } from '../shared/resolucion.service';
import { forkJoin, switchMap, map } from 'rxjs';
@Component({
  selector: 'app-resolucion-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './resolucion-form.html',
  styleUrls: ['./resolucion-form.css']
})
export class ResolucionFormComponent implements OnInit {
  private service = inject(ResolucionService);

  clientes: any[] = [];
  tiposDoc: any[] = [];

  // Modelo del formulario
  resolucion: Resolucion = this.reset();

  // editar si llega un objeto con idResolucion
  private editableTemp: Resolucion | null = null;


  @Input() set resolucionEditar(r: Resolucion | null | undefined) {
  if (r && r.idResolucion) {
    this.editableTemp = r;
  } else {
    this.resetForm();
    this.editableTemp = null;
  }
}

ngOnInit(): void {
  this.loadSelects();
}

private loadSelects() {
  forkJoin({
    clientes: this.service.getClientes(),
    tiposDoc: this.service.getTiposDocumento()
  }).subscribe({
    next: ({clientes, tiposDoc}) => {
      this.clientes = clientes || [];
      this.tiposDoc = tiposDoc || [];

   
      if (this.editableTemp) {
        this.fillFromEditable();
      }
    },
    error: e => console.error(e)
  });
}

private fillFromEditable() {
  if (!this.editableTemp) return;

  this.resolucion = {
    ...this.editableTemp,
    cliente: this.clientes.find(c => c.id === this.editableTemp?.cliente?.id) || this.editableTemp.cliente,
    tipoDocumento: this.tiposDoc.find(t => t.idTipoDocumento === this.editableTemp?.tipoDocumento?.idTipoDocumento) || this.editableTemp.tipoDocumento
  };
}

  @Output() saved = new EventEmitter<void>();
  @Output() cancelled = new EventEmitter<void>();


  guardar(e?: Event) {
    if (e) e.preventDefault();

    // Validaciones
    if (!this.resolucion.cliente?.id) { alert('Seleccione cliente'); return; }
    if (!this.resolucion.tipoDocumento?.idTipoDocumento) { alert('Seleccione tipo de documento'); return; }
    if (!this.resolucion.numeroResolucion) { alert('Ingrese número de resolución'); return; }
    if (!this.resolucion.fechaCreacion) { alert('Ingrese fecha de creación'); return; }
    if (!this.resolucion.desde || !this.resolucion.hasta) { alert('Ingrese rango desde/hasta'); return; }

    // Construir DTO tal como espera backend (en tu JS original enviabas cliente:{id}, tipoDocumento:{idTipoDocumento})
    const dto: any = {
      clienteId: this.resolucion.cliente?.id,
      tipoDocumentoId: this.resolucion.tipoDocumento?.idTipoDocumento,
      numeroResolucion: this.resolucion.numeroResolucion,
      prefijo: this.resolucion.prefijo,
      fechaCreacion: this.resolucion.fechaCreacion,
      llaveTecnica: this.resolucion.llaveTecnica,
      desde: this.resolucion.desde,
      hasta: this.resolucion.hasta,
      fechaDesde: this.resolucion.fechaDesde,
      fechaHasta: this.resolucion.fechaHasta
    };


    // Si viene idResolucion -> actualizar
    if (this.resolucion.idResolucion) {
      dto.idResolucion = this.resolucion.idResolucion;
      this.service.updateResolucion(dto).subscribe({
        next: () => { alert('Resolución actualizada'); this.afterSave(); },
        error: (err) => { console.error(err); alert('Error al actualizar'); }
      });
      return;
    }

    // crear: eliminar id por si acaso
    delete dto.idResolucion;
    this.service.createResolucion(dto).subscribe({
      next: () => { alert('Resolución creada'); this.afterSave(); },
      error: (err) => { console.error(err); alert('Error al crear'); }
    });
  }

  private afterSave() {
    this.resetForm();
    this.saved.emit();
  }

  cancelar() {
    this.resetForm();
    this.cancelled.emit();
  }

  resetForm() {
    this.resolucion = this.reset();
  }

  private reset(): Resolucion {
    return {
      idResolucion: undefined,
      cliente: undefined,
      tipoDocumento: undefined,
      numeroResolucion: '',
      prefijo: '',
      fechaCreacion: '',
      llaveTecnica: '',
      desde: undefined,
      hasta: undefined,
      fechaDesde: '',
      fechaHasta: ''
    };
  }
}
