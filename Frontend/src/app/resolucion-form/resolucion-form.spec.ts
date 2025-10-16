import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResolucionForm } from './resolucion-form';

describe('ResolucionForm', () => {
  let component: ResolucionForm;
  let fixture: ComponentFixture<ResolucionForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResolucionForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResolucionForm);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
