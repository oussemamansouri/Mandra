import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuestHousesComponent } from './guest-houses.component';

describe('GuestHousesComponent', () => {
  let component: GuestHousesComponent;
  let fixture: ComponentFixture<GuestHousesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GuestHousesComponent]
    });
    fixture = TestBed.createComponent(GuestHousesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
