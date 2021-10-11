import { TestBed } from '@angular/core/testing';

import { ResaService } from './resa.service';

describe('ResaService', () => {
  let service: ResaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
