import { Cliente } from './cliente';
import { Departamento } from './departamento';
import { Municipio } from './municipio';

describe('Cliente Model', () => {
  it('should create an instance', () => {
    const cliente = new Cliente();
    expect(cliente).toBeTruthy();
  });

  it('should assign values correctly', () => {
    const departamento = new Departamento();
    departamento.id = 1;
    departamento.nombre = 'Antioquia';

    const municipio = new Municipio();
    municipio.id = 10;
    municipio.nombre = 'Medellín';
    municipio.codigo = 'MED';

    const cliente = new Cliente();
    cliente.id = 5;
    cliente.razonSocial = 'Comercial ABC S.A.S';
    cliente.nit = '900123456';
    cliente.dv = '1';
    cliente.departamento = departamento;
    cliente.municipio = municipio;
    cliente.email = 'contacto@abc.com';

    expect(cliente.id).toBe(5);
    expect(cliente.razonSocial).toBe('Comercial ABC S.A.S');
    expect(cliente.departamento?.nombre).toBe('Antioquia');
    expect(cliente.municipio?.nombre).toBe('Medellín');
    expect(cliente.email).toContain('@');
  });
});
