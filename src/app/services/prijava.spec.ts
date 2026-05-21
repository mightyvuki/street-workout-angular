import { TestBed } from '@angular/core/testing';

import { Prijava } from './prijava';

describe('Prijava', () => {
  let service: Prijava;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Prijava);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
