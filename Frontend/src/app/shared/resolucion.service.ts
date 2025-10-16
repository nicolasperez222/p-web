import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Resolucion } from '../model/resolucion';
import { forkJoin, switchMap, map } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ResolucionService {
  private http = inject(HttpClient);

  private API_RESOLUCIONES = 'http://localhost:8080/api/resoluciones';
  private API_CLIENTES = 'http://localhost:8080/api/clientes';
  private API_TIPOS_DOC = 'http://localhost:8080/api/tipos-documento';

  getResoluciones(): Observable<Resolucion[]> {
  return this.http.get<any[]>(this.API_RESOLUCIONES).pipe(
    switchMap(resList =>
      forkJoin({
        clientes: this.getClientes(),
        tiposDoc: this.getTiposDocumento()
      }).pipe(
        map(({ clientes, tiposDoc }) =>
          resList.map(r => {
            const cliente = clientes.find(c => c.id === r.clienteId);
            const tipoDocumento = tiposDoc.find(t => t.idTipoDocumento === r.tipoDocumentoId);

            return {
              idResolucion: r.idResolucion,
              cliente: cliente
                ? { id: cliente.id, razonSocial: cliente.razonSocial }
                : { id: r.clienteId, razonSocial: '' },

              tipoDocumento: tipoDocumento
                ? { idTipoDocumento: tipoDocumento.idTipoDocumento, nombre: tipoDocumento.nombre }
                : { idTipoDocumento: r.tipoDocumentoId, nombre: '' },

              numeroResolucion: r.numeroResolucion,
              prefijo: r.prefijo,
              fechaCreacion: r.fechaCreacion,
              llaveTecnica: r.llaveTecnica,
              desde: r.desde,
              hasta: r.hasta,
              fechaDesde: r.fechaDesde,
              fechaHasta: r.fechaHasta
            } as Resolucion;
          })
        )
      )
    )
  );
}


  createResolucion(res: Resolucion): Observable<any> {
    return this.http.post(this.API_RESOLUCIONES, res);
  }

  updateResolucion(res: any): Observable<any> {
  return this.http.post(this.API_RESOLUCIONES, res);
}

  deleteResolucion(id: number): Observable<any> {
    return this.http.delete(`${this.API_RESOLUCIONES}/${id}`);
  }

  getClientes(): Observable<any[]> {
    return this.http.get<any[]>(this.API_CLIENTES);
  }

  getTiposDocumento(): Observable<any[]> {
    return this.http.get<any[]>(this.API_TIPOS_DOC);
  }
}
