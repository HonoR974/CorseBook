import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicationPublicComponent } from './publication-public.component';

describe('PublicationPublicComponent', () => {
  let component: PublicationPublicComponent;
  let fixture: ComponentFixture<PublicationPublicComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PublicationPublicComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PublicationPublicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
