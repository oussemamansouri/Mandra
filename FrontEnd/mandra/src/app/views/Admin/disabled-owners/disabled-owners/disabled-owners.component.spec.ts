import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisabledOwnersComponent } from './disabled-owners.component';

describe('DisabledOwnersComponent', () => {
  let component: DisabledOwnersComponent;
  let fixture: ComponentFixture<DisabledOwnersComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DisabledOwnersComponent]
    });
    fixture = TestBed.createComponent(DisabledOwnersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
