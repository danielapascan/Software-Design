import { TestBed } from '@angular/core/testing';

import { PopupDonationsService } from './popup-donations.service';

describe('PopupDonationsService', () => {
  let service: PopupDonationsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PopupDonationsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
