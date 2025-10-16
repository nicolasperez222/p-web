import { Municipio } from './municipio';

describe('Municipio Model', () => {
  it('should create an instance', () => {
    const municipio = new Municipio();
    expect(municipio).toBeTruthy();
  });

  it('should assign properties correctly', () => {
    const municipio = new Municipio();
    municipio.id = 101;
    municipio.nombre = 'Medellín';
    municipio.codigo = 'MED';

    expect(municipio.id).toBe(101);
    expect(municipio.nombre).toBe('Medellín');
    expect(municipio.codigo).toBe('MED');
  });
});
