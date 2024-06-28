import { TestBed } from '@angular/core/testing';

import { StorageServiceService } from './storage-service.service';

describe('StorageServiceService', () => {
  let service: StorageServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StorageServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
