import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin, switchMap, map } from 'rxjs';
import { DocumentoEmitido } from '../model/documento-emitido';

@Injectable({ providedIn: 'root' })
export class DocumentoEmitidoService {
  private http = inject(HttpClient);

  private API = 'http://localhost:8080/api/documentos-emitidos';
  private API_CLIENTES = 'http://localhost:8080/api/clientes';
  private API_TIPOS_DOC = 'http://localhost:8080/api/tipos-documento';
  private API_RESOLUCIONES = 'http://localhost:8080/api/resoluciones';

  getDocumentos(): Observable<DocumentoEmitido[]> {
    return this.http.get<any[]>(this.API).pipe(
      switchMap(docs =>
        forkJoin({
          clientes: this.getClientes(),
          tiposDoc: this.getTiposDocumento(),
          resoluciones: this.getResoluciones()
        }).pipe(
          map(({ clientes, tiposDoc, resoluciones }) =>
            docs.map(d => ({
              ...d,
              cliente: clientes.find(c => c.id === d.clienteId),
              tipoDocumento: tiposDoc.find(t => t.idTipoDocumento === d.tipoDocumentoId),
              resolucion: resoluciones.find(r => r.idResolucion === d.resolucionId)
            }))
          )
        )
      )
    );
  }

  createDocumento(dto: DocumentoEmitido): Observable<any> {
    return this.http.post(this.API, dto);
  }

  updateDocumento(dto: DocumentoEmitido): Observable<any> {
    return this.http.post(this.API, dto);}

  deleteDocumento(id: number): Observable<any> {
    return this.http.delete(`${this.API}/${id}`);
  }

  getClientes(): Observable<any[]> {
    return this.http.get<any[]>(this.API_CLIENTES);
  }

  getTiposDocumento(): Observable<any[]> {
    return this.http.get<any[]>(this.API_TIPOS_DOC);
  }

  getResoluciones(): Observable<any[]> {
    return this.http.get<any[]>(this.API_RESOLUCIONES);
  }
}
