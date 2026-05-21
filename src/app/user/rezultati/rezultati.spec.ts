import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Rezultati } from './rezultati';

describe('Rezultati', () => {
  let component: Rezultati;
  let fixture: ComponentFixture<Rezultati>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Rezultati]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Rezultati);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
