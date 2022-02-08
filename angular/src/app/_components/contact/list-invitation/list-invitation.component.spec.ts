import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListInvitationComponent } from './list-invitation.component';

describe('ListInvitationComponent', () => {
  let component: ListInvitationComponent;
  let fixture: ComponentFixture<ListInvitationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListInvitationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListInvitationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
