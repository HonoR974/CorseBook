import { TestBed } from '@angular/core/testing';

import { UploadS3Service } from './upload-s3.service';

describe('UploadS3Service', () => {
  let service: UploadS3Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UploadS3Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
