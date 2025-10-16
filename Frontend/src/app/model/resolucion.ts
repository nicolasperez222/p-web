export interface ClienteRef {
  id?: number;
  razonSocial?: string;
}

export interface TipoDocumentoRef {
  idTipoDocumento?: number;
  nombre?: string;
}

export interface Resolucion {
  idResolucion?: number;
  cliente?: ClienteRef;
  tipoDocumento?: TipoDocumentoRef;
  numeroResolucion?: string;
  prefijo?: string;
  fechaCreacion?: string;
  llaveTecnica?: string;
  desde?: number;
  hasta?: number;
  fechaDesde?: string; 
  fechaHasta?: string;
}
