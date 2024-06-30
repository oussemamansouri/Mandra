import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddOwnerComponent } from './add-owner.component';

describe('AddOwnerComponent', () => {
  let component: AddOwnerComponent;
  let fixture: ComponentFixture<AddOwnerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddOwnerComponent]
    });
    fixture = TestBed.createComponent(AddOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
