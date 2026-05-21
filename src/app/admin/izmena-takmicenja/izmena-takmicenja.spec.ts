import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IzmenaTakmicenjaComponent } from './izmena-takmicenja';

describe('IzmenaTakmicenjaComponent', () => {
  let component: IzmenaTakmicenjaComponent;
  let fixture: ComponentFixture<IzmenaTakmicenjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IzmenaTakmicenjaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IzmenaTakmicenjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
