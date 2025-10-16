import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DocumentoEmitidoService } from '../shared/documento-emitido.service';
import { DocumentoEmitido } from '../model/documento-emitido';
import { DocumentoEmitidoFormComponent } from '../documento-emitido-form/documento-emitido-form.component';

@Component({
  selector: 'app-documento-emitido-list',
  standalone: true,
  imports: [CommonModule, DocumentoEmitidoFormComponent],
  templateUrl: './documento-emitido-list.component.html',
  styleUrls: ['./documento-emitido-list.component.css']
})
export class DocumentoEmitidoListComponent implements OnInit {
  private service = inject(DocumentoEmitidoService);
  documentos = signal<DocumentoEmitido[]>([]);
  documentoSeleccionado: DocumentoEmitido | null | undefined = undefined;
  mostrarFormulario = false;

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.service.getDocumentos().subscribe({
      next: d => this.documentos.set(d || []),
      error: e => console.error(e)
    });
  }

  nuevo() {
    this.documentoSeleccionado = null;
    this.mostrarFormulario = true;
  }

  editar(doc: DocumentoEmitido) {
    this.documentoSeleccionado = { ...doc };
    this.mostrarFormulario = true;
  }

  eliminar(id?: number) {
    if (!id) return;
    if (!confirm('¿Eliminar documento?')) return;
    this.service.deleteDocumento(id).subscribe({
      next: () => this.load(),
      error: e => console.error(e)
    });
  }

  onSaved() {
    this.mostrarFormulario = false;
    this.documentoSeleccionado = undefined;
    this.load();
  }

  onCancel() {
    this.mostrarFormulario = false;
    this.documentoSeleccionado = undefined;
  }
}
