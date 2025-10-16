import { Certificado } from './certificado';
import { Cliente } from './cliente';

describe('Certificado Model', () => {
  it('should create an instance', () => {
    const cert = new Certificado();
    expect(cert).toBeTruthy();
  });

  it('should assign values correctly', () => {
    const cliente = new Cliente();
    cliente.id = 1;
    cliente.razonSocial = 'Tech Solutions SAS';

    const cert = new Certificado();
    cert.idCertificado = 100;
    cert.nombreArchivo = 'certificado.pdf';
    cert.data = 'base64string';
    cert.vigenciaAnios = 2;
    cert.vencimiento = new Date('2026-10-15');
    cert.fechaRegistro = new Date('2025-10-15');
    cert.cliente = cliente;

    expect(cert.idCertificado).toBe(100);
    expect(cert.nombreArchivo).toBe('certificado.pdf');
    expect(cert.data).toBe('base64string');
    expect(cert.vigenciaAnios).toBe(2);
    expect(cert.vencimiento instanceof Date).toBeTrue();
    expect(cert.cliente?.razonSocial).toBe('Tech Solutions SAS');
  });
});
