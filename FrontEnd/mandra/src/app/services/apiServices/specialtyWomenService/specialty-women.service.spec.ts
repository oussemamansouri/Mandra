import { TestBed } from '@angular/core/testing';

import { SpecialtyWomenService } from './specialty-women.service';

describe('SpecialtyWomenService', () => {
  let service: SpecialtyWomenService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SpecialtyWomenService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
