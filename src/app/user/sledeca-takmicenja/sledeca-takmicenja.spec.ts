import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SledecaTakmicenja } from './sledeca-takmicenja';

describe('SledecaTakmicenja', () => {
  let component: SledecaTakmicenja;
  let fixture: ComponentFixture<SledecaTakmicenja>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SledecaTakmicenja]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SledecaTakmicenja);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
