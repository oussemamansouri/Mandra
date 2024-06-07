import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerLayouteComponent } from './owner-layoute.component';

describe('OwnerLayouteComponent', () => {
  let component: OwnerLayouteComponent;
  let fixture: ComponentFixture<OwnerLayouteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OwnerLayouteComponent]
    });
    fixture = TestBed.createComponent(OwnerLayouteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
