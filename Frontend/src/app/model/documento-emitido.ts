export interface DocumentoEmitido {
  idDocumento?: number;
  numero: string;
  tipoDocumentoId?: number;
  resolucionId?: number;
  fecha: string;
  valorBruto?: number;
  descuentos?: number;
  subtotal?: number;
  impuestos?: number;
  total?: number;
  cufeCude?: string;
  clienteId?: number;
  jsonPeticion?: string;

  cliente?: any;
  tipoDocumento?: any;
  resolucion?: any;
}
