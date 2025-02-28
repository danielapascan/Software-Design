import { TestBed } from '@angular/core/testing';

import { PopupSalaryService } from './popup-salary.service';

describe('PopupSalaryService', () => {
  let service: PopupSalaryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PopupSalaryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
