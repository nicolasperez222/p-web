import { Component, OnInit, inject, signal, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Cliente } from '../model/cliente';
import { ClienteService } from '../shared/cliente.service';

@Component({
  selector: 'app-cliente-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cliente-form.html',
  styleUrls: ['./cliente-form.css']
})
export class ClienteFormComponent implements OnInit {
  private service = inject(ClienteService);

  departamentos = signal<any[]>([]);
  municipios = signal<any[]>([]);

  cliente: Cliente = this.resetCliente();

  private clienteEditarTemp: Cliente | null = null;

  editing = signal(false);
  clienteId?: number;

  @Output() clienteGuardado = new EventEmitter<void>();

  logoBase64 = signal<string>('');

  tiposEmpresa = [
    { id: 1, nombre: 'Persona Natural' },
    { id: 2, nombre: 'Persona Jurídica' }
  ];

  @Input() set clienteEditar(cliente: Cliente | null | undefined) {
    if (cliente && cliente.id) {
      this.clienteEditarTemp = cliente;
      if (this.departamentos().length > 0 && this.municipios().length > 0) {
        this.cargarClienteEditar();
      }
    } else {
      this.resetForm();
      this.editing.set(false);
      this.clienteEditarTemp = null;
    }
  }

  ngOnInit(): void {
    this.cargarSelects();
  }

  private cargarSelects() {
    let depLoaded = false;
    let munLoaded = false;

    this.service.getDepartamentos().subscribe({
      next: d => {
        this.departamentos.set(d || []);
        depLoaded = true;
        if (munLoaded) this.cargarClienteEditar();
      },
      error: err => {
        console.error('Error cargando departamentos', err);
        this.departamentos.set([]);
        depLoaded = true;
        if (munLoaded) this.cargarClienteEditar();
      }
    });

    this.service.getMunicipios().subscribe({
      next: m => {
        this.municipios.set(m || []);
        munLoaded = true;
        if (depLoaded) this.cargarClienteEditar();
      },
      error: err => {
        console.error('Error cargando municipios', err);
        this.municipios.set([]);
        munLoaded = true;
        if (depLoaded) this.cargarClienteEditar();
      }
    });
  }

  private cargarClienteEditar() {
    if (!this.clienteEditarTemp || !this.clienteEditarTemp.id) {
      return;
    }

    const departamento = this.departamentos().find(d => d.id === (this.clienteEditarTemp as any).departamentoId) || (this.clienteEditarTemp as any).departamento || null;
    const municipio = this.municipios().find(m => m.id === (this.clienteEditarTemp as any).municipioId) || (this.clienteEditarTemp as any).municipio || null;

    this.cliente = {
      ...this.clienteEditarTemp,
      departamento: departamento || (this.clienteEditarTemp as any).departamento || {} as any,
      municipio: municipio || (this.clienteEditarTemp as any).municipio || {} as any
    };

    this.clienteId = this.clienteEditarTemp.id;
    this.editing.set(true);
    this.logoBase64.set(this.clienteEditarTemp.logo || '');
  }

  guardarCliente() {
    const clienteDTO: any = {
      ...this.cliente,
      departamentoId: (this.cliente as any).departamento?.id ?? (this.cliente as any).departamentoId ?? null,
      municipioId: (this.cliente as any).municipio?.id ?? (this.cliente as any).municipioId ?? null,
      logo: this.logoBase64()
    };

    if (this.editing() && this.clienteId) {
      clienteDTO.id = this.clienteId;
      this.service.update(clienteDTO).subscribe({
        next: (msg: string) => {
          alert(msg);
          this.resetForm();
          this.clienteGuardado.emit();
        },
        error: (err: any) => {
          console.error('Error al editar cliente', err);
          alert('Error al editar cliente: ' + (err?.error || err?.message || err));
        }
      });
      return;
    }


    if (this.editing()) {
      clienteDTO.id = this.clienteId;
    } else {
      delete clienteDTO.id;
      delete clienteDTO.clienteId;
      this.clienteId = undefined; 
    }


    if (!clienteDTO.nit || !clienteDTO.razonSocial) {
      alert('Debe ingresar al menos NIT y Razón Social.');
      return;
    }
    if (!clienteDTO.departamentoId || !clienteDTO.municipioId) {
      alert('Seleccione Departamento y Municipio.');
      return;
    }

    this.service.create(clienteDTO).subscribe({
      next: (msg: string) => {
        alert(msg);
        this.resetForm();
        this.clienteGuardado.emit();
      },
      error: (err: any) => {
        console.error('Error al guardar cliente', err);
        alert('Error al guardar cliente: ' + (err?.error || err?.message || err));
      }
    });
  }

  resetForm() {
    this.cliente = this.resetCliente();
    this.logoBase64.set('');
    this.clienteId = undefined;
    this.editing.set(false);
  }

  private resetCliente(): Cliente {
    return {
      razonSocial: '',
      representanteLegal: '',
      nit: '',
      dv: '',
      tipoEmpresa: 1,
      departamento: {} as any,
      municipio: {} as any,
      logo: '',
      email: '',
      telefono: ''
    };
  }

  handleLogo(event: Event) {
    const file = (event.target as HTMLInputElement).files?.[0];
    if (!file) return;
    const reader = new FileReader();
    reader.onloadend = () => this.logoBase64.set(reader.result as string);
    reader.readAsDataURL(file);
  }
}
