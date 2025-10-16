import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ClienteService } from './cliente.service';
import { Cliente } from '../model/cliente';

describe('ClienteService', () => {
  let service: ClienteService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ClienteService]
    });
    service = TestBed.inject(ClienteService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should retrieve clients from API via GET', () => {
    const mockClientes: Cliente[] = [
      { id: 1, razonSocial: 'Empresa A', nit: '123', email: 'a@empresa.com' },
      { id: 2, razonSocial: 'Empresa B', nit: '456', email: 'b@empresa.com' }
    ];

    service.findAll().subscribe(clientes => {
      expect(clientes.length).toBe(2);
      expect(clientes).toEqual(mockClientes);
    });

    const req = httpMock.expectOne('http://localhost:8080/api/clientes');
    expect(req.request.method).toBe('GET');
    req.flush(mockClientes);
  });
});
