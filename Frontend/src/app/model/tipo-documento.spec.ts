import { TipoDocumento } from './tipo-documento';

describe('TipoDocumento Model', () => {
  it('should create an instance', () => {
    const tipo = new TipoDocumento();
    expect(tipo).toBeTruthy();
  });

  it('should assign values correctly', () => {
    const tipo = new TipoDocumento();
    tipo.idTipoDocumento = 1;
    tipo.nombre = 'Factura Electrónica';
    tipo.codigo = 'FE';

    expect(tipo.idTipoDocumento).toBe(1);
    expect(tipo.nombre).toBe('Factura Electrónica');
    expect(tipo.codigo).toBe('FE');
  });
});
