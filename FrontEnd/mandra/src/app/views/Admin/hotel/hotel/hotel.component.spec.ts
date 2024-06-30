import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelComponent } from './hotel.component';

describe('HotelComponent', () => {
  let component: HotelComponent;
  let fixture: ComponentFixture<HotelComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HotelComponent]
    });
    fixture = TestBed.createComponent(HotelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
