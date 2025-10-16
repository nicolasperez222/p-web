import { DocumentoEmitido } from './documento-emitido';
import { Cliente } from './cliente';
import { TipoDocumento } from './tipo-documento';
import { Resolucion } from './resolucion';

describe('DocumentoEmitido Model', () => {
  it('should create an instance', () => {
    const doc = new DocumentoEmitido();
    expect(doc).toBeTruthy();
  });

  it('should assign values correctly', () => {
    const tipo = new TipoDocumento();
    const res = new Resolucion();
    const cli = new Cliente();

    const doc = new DocumentoEmitido();
    doc.idDocumento = 1;
    doc.numero = 'FAC-001';
    doc.tipoDocumento = tipo;
    doc.resolucion = res;
    doc.fecha = new Date('2025-10-15');
    doc.valorBruto = 1000.0;
    doc.descuentos = 50.0;
    doc.subtotal = 950.0;
    doc.impuestos = 180.5;
    doc.total = 1130.5;
    doc.cufeCude = 'XYZ123ABC';
    doc.cliente = cli;
    doc.jsonPeticion = '{"detalle":"ok"}';

    expect(doc.idDocumento).toBe(1);
    expect(doc.numero).toBe('FAC-001');
    expect(doc.tipoDocumento).toBe(tipo);
    expect(doc.resolucion).toBe(res);
    expect(doc.fecha).toEqual(new Date('2025-10-15'));
    expect(doc.total).toBe(1130.5);
    expect(doc.cliente).toBe(cli);
    expect(doc.jsonPeticion).toContain('ok');
  });
});
