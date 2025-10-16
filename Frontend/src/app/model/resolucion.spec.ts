import { Resolucion } from './resolucion';
import { Cliente } from './cliente';
import { TipoDocumento } from './tipo-documento';

describe('Resolucion Model', () => {
  it('should create an instance', () => {
    const resolucion = new Resolucion();
    expect(resolucion).toBeTruthy();
  });

  it('should assign values correctly', () => {
    const cliente = new Cliente();
    cliente.id = 1;
    cliente.razonSocial = 'Tech Solutions SAS';

    const tipoDoc = new TipoDocumento();
    tipoDoc.idTipoDocumento = 2;
    tipoDoc.nombre = 'Factura Electrónica';
    tipoDoc.codigo = 'FE';

    const resolucion = new Resolucion();
    resolucion.idResolucion = 10;
    resolucion.prefijo = 'FAC';
    resolucion.numeroResolucion = '12345';
    resolucion.fechaCreacion = new Date('2025-10-15');
    resolucion.llaveTecnica = 'ABC123XYZ';
    resolucion.desde = 1;
    resolucion.hasta = 1000;
    resolucion.fechaDesde = new Date('2025-10-01');
    resolucion.fechaHasta = new Date('2026-09-30');
    resolucion.cliente = cliente;
    resolucion.tipoDocumento = tipoDoc;

    expect(resolucion.idResolucion).toBe(10);
    expect(resolucion.prefijo).toBe('FAC');
    expect(resolucion.numeroResolucion).toBe('12345');
    expect(resolucion.cliente?.razonSocial).toBe('Tech Solutions SAS');
    expect(resolucion.tipoDocumento?.codigo).toBe('FE');
    expect(resolucion.fechaHasta instanceof Date).toBeTrue();
  });
});
