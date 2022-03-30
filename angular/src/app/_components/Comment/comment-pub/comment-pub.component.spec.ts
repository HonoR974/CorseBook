import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommentPubComponent } from './comment-pub.component';

describe('CommentPubComponent', () => {
  let component: CommentPubComponent;
  let fixture: ComponentFixture<CommentPubComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommentPubComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommentPubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
