import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuastHouseComponent } from './guast-house.component';

describe('GuastHouseComponent', () => {
  let component: GuastHouseComponent;
  let fixture: ComponentFixture<GuastHouseComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GuastHouseComponent]
    });
    fixture = TestBed.createComponent(GuastHouseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
