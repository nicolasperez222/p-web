import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cliente } from '../model/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private http = inject(HttpClient);
  private baseUrl = 'http://localhost:8080/api';

  findAll(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${this.baseUrl}/clientes`);
  }

  findById(id: number): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.baseUrl}/clientes/${id}`);
  }

  create(cliente: Cliente): Observable<string> {
    return this.http.post(`${this.baseUrl}/clientes`, cliente, { responseType: 'text' });
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/clientes/${id}`);
  }
  
  update(cliente: Cliente): Observable<string> {
    return this.http.post(`${this.baseUrl}/clientes`, cliente, { responseType: 'text' });
  }


  getDepartamentos(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/departamentos`);
  }

  getMunicipios(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/municipios`);
  }

  getTiposEmpresa(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/tipo-empresa`);
  }
}
