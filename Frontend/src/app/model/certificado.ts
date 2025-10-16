import { Cliente } from './cliente';

export class Certificado {
  idCertificado?: number;
  nombreArchivo?: string;
  data?: string;
  vigenciaAnios?: number;
  vencimiento?: Date;
  fechaRegistro?: Date;
  cliente?: Cliente;
}
