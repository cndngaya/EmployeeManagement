import { TestBed } from '@angular/core/testing';

import { EmployeListServiceService } from './employe-list-service.service';

describe('EmployeListServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EmployeListServiceService = TestBed.get(EmployeListServiceService);
    expect(service).toBeTruthy();
  });
});
