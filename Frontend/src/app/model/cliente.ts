import { Departamento } from './departamento';
import { Municipio } from './municipio';

export class Cliente {
  id?: number;
  representanteLegal?: string;
  nit?: string;
  dv?: string;
  razonSocial?: string;
  tipoEmpresa?: number;
  responsabilidadTributaria?: string;
  regimenIva?: string;
  direccion?: string;
  email?: string;
  telefono?: string;
  codigoCiiu?: string;
  impuesto?: string;
  estado?: number;
  departamento?: Departamento;
  municipio?: Municipio;
  logo?: string;
  departamentoNombre?: string;
  municipioNombre?: string;
  departamentoId?: string;
  municipioId?: string;
}
