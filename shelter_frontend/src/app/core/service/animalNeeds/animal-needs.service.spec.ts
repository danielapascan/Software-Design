import { TestBed } from '@angular/core/testing';

import { AnimalNeedsService } from './animal-needs.service';

describe('AnimalNeedsService', () => {
  let service: AnimalNeedsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnimalNeedsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
