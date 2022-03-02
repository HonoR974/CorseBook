import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicationProfileComponent } from './publication-profile.component';

describe('PublicationProfileComponent', () => {
  let component: PublicationProfileComponent;
  let fixture: ComponentFixture<PublicationProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PublicationProfileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PublicationProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
