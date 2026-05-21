import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NajavljenoComponent } from './najavljeno';

describe('NajavljenoComponent', () => {
  let component: NajavljenoComponent;
  let fixture: ComponentFixture<NajavljenoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NajavljenoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NajavljenoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
