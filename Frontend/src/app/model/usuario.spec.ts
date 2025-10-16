import { Usuario } from './usuario';

describe('Usuario Model', () => {
  it('should create an instance', () => {
    const user = new Usuario();
    expect(user).toBeTruthy();
  });

  it('should assign values correctly', () => {
    const user = new Usuario();
    user.idUsuario = 1;
    user.usuario = 'admin';
    user.senhia = '123456';
    user.cliente = 10;

    expect(user.idUsuario).toBe(1);
    expect(user.usuario).toBe('admin');
    expect(user.senhia).toBe('123456');
    expect(user.cliente).toBe(10);
  });
});
