import { TestBed } from '@angular/core/testing';

import { GuastHouseService } from './guast-house.service';

describe('GuastHouseService', () => {
  let service: GuastHouseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GuastHouseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
