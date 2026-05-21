import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Odrzano } from './odrzano';

describe('Odrzano', () => {
  let component: Odrzano;
  let fixture: ComponentFixture<Odrzano>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Odrzano]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Odrzano);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
