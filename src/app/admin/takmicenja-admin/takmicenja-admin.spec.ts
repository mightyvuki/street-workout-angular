import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TakmicenjaAdmin } from './takmicenja-admin';

describe('TakmicenjaAdmin', () => {
  let component: TakmicenjaAdmin;
  let fixture: ComponentFixture<TakmicenjaAdmin>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TakmicenjaAdmin]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TakmicenjaAdmin);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
