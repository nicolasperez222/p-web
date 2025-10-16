
import { Component, OnInit, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClienteFormComponent } from '../cliente-form/cliente-form';
import { Cliente } from '../model/cliente';
import { ClienteService } from '../shared/cliente.service';

@Component({
  selector: 'app-cliente-list',
  standalone: true,
  imports: [CommonModule, ClienteFormComponent],
  templateUrl: './cliente-list.component.html',
  styleUrls: ['./cliente-list.component.css']
})
export class ClienteListComponent implements OnInit {
  private service = inject(ClienteService);

  clientes = signal<Cliente[]>([]);
  clienteSeleccionado: Cliente | null = null;

  ngOnInit(): void {
    this.cargarClientes();
  }

  cargarClientes() {
    this.service.findAll().subscribe({
      next: (data) => {
        this.clientes.set(data || []);
      },
      error: (err) => console.error('Error al cargar clientes', err)
    });
  }

  eliminarCliente(id?: number) {
    if (!id) return;
    if (!confirm('¿Seguro de eliminar este cliente?')) return;
    this.service.delete(id).subscribe({
      next: () => this.cargarClientes(),
      error: (err) => console.error('Error al eliminar cliente', err)
    });
  }

 
mostrarFormulario = false;

nuevoCliente() {
  this.clienteSeleccionado = null; 
  this.mostrarFormulario = true;
}

editarCliente(cliente: Cliente) {
  this.clienteSeleccionado = { ...cliente };
  this.mostrarFormulario = true;
}

onClienteGuardado() {
  this.mostrarFormulario = false;
  this.cargarClientes();
}

}
