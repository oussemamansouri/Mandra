import { TestBed } from '@angular/core/testing';

import { GastronomicSpecialtiesService } from './gastronomic-specialties.service';

describe('GastronomicSpecialtiesService', () => {
  let service: GastronomicSpecialtiesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GastronomicSpecialtiesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
