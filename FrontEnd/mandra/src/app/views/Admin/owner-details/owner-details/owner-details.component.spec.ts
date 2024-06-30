import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerDetailsComponent } from './owner-details.component';

describe('OwnerDetailsComponent', () => {
  let component: OwnerDetailsComponent;
  let fixture: ComponentFixture<OwnerDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OwnerDetailsComponent]
    });
    fixture = TestBed.createComponent(OwnerDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
