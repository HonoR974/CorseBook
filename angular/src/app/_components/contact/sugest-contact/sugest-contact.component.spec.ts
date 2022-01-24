import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SugestContactComponent } from './sugest-contact.component';

describe('SugestContactComponent', () => {
  let component: SugestContactComponent;
  let fixture: ComponentFixture<SugestContactComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SugestContactComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SugestContactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
