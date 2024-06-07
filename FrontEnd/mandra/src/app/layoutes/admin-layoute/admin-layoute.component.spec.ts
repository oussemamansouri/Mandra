import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminLayouteComponent } from './admin-layoute.component';

describe('AdminLayouteComponent', () => {
  let component: AdminLayouteComponent;
  let fixture: ComponentFixture<AdminLayouteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminLayouteComponent]
    });
    fixture = TestBed.createComponent(AdminLayouteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
