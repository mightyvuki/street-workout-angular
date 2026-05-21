import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NovoTakmicenjeComponent } from './novo-takmicenje';

describe('NovoTakmicenjeComponent', () => {
  let component: NovoTakmicenjeComponent;
  let fixture: ComponentFixture<NovoTakmicenjeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NovoTakmicenjeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NovoTakmicenjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
