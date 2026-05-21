import { TestBed } from '@angular/core/testing';

import { Takmicenje } from './takmicenje';

describe('Takmicenje', () => {
  let service: Takmicenje;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Takmicenje);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
