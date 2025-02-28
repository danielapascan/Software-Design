import { TestBed } from '@angular/core/testing';

import { PopupAdoptionsService } from './popup-adoptions.service';

describe('PopupAdoptionsService', () => {
  let service: PopupAdoptionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PopupAdoptionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
