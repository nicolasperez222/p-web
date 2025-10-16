import { Component, OnInit, Input, Output, EventEmitter, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DocumentoEmitido } from '../model/documento-emitido';
import { DocumentoEmitidoService } from '../shared/documento-emitido.service';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-documento-emitido-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './documento-emitido-form.component.html',
  styleUrls: ['./documento-emitido-form.component.css']
})
export class DocumentoEmitidoFormComponent implements OnInit {
  private service = inject(DocumentoEmitidoService);

  clientes: any[] = [];
  tiposDoc: any[] = [];
  resoluciones: any[] = [];

  documento: DocumentoEmitido = this.reset();
  private editableTemp: DocumentoEmitido | null = null;

  @Input() set documentoEditar(d: DocumentoEmitido | null | undefined) {
    if (d && d.idDocumento) this.editableTemp = d;
    else this.resetForm();
  }

  @Output() saved = new EventEmitter<void>();
  @Output() cancelled = new EventEmitter<void>();

  ngOnInit(): void {
    forkJoin({
      clientes: this.service.getClientes(),
      tiposDoc: this.service.getTiposDocumento(),
      resoluciones: this.service.getResoluciones()
    }).subscribe({
      next: ({ clientes, tiposDoc, resoluciones }) => {
        this.clientes = clientes;
        this.tiposDoc = tiposDoc;
        this.resoluciones = resoluciones;
        if (this.editableTemp) this.fillFromEditable();
      },
      error: e => console.error(e)
    });
  }

  private fillFromEditable() {
    if (!this.editableTemp) return;
    this.documento = { ...this.editableTemp };
  }

  guardar(e?: Event) {
    if (e) e.preventDefault();
    const dto = {
      idDocumento: this.documento.idDocumento,
      numero: this.documento.numero,
      tipoDocumentoId: this.documento.tipoDocumentoId,
      resolucionId: this.documento.resolucionId,
      fecha: this.documento.fecha,
      valorBruto: this.documento.valorBruto,
      descuentos: this.documento.descuentos,
      clienteId: this.documento.clienteId,
      jsonPeticion: this.documento.jsonPeticion
    };

    const req = dto.idDocumento
      ? this.service.updateDocumento(dto)
      : this.service.createDocumento(dto);

    req.subscribe({
      next: () => {
        alert('Documento guardado correctamente');
        this.afterSave();
      },
      error: e => {
        console.error(e);
        alert('Error al guardar');
      }
    });
  }

  cancelar() {
    this.resetForm();
    this.cancelled.emit();
  }

  private afterSave() {
    this.resetForm();
    this.saved.emit();
  }

  resetForm() {
    this.documento = this.reset();
  }

  private reset(): DocumentoEmitido {
    return {
      numero: '',
      fecha: '',
      valorBruto: 0,
      descuentos: 0,
      subtotal: 0,
      impuestos: 0,
      total: 0,
      cufeCude: '',
      clienteId: undefined,
      tipoDocumentoId: undefined,
      resolucionId: undefined,
      jsonPeticion: ''
    };
  }
}
