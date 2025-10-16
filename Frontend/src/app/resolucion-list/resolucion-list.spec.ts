import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResolucionList } from './resolucion-list';

describe('ResolucionList', () => {
  let component: ResolucionList;
  let fixture: ComponentFixture<ResolucionList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResolucionList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResolucionList);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
