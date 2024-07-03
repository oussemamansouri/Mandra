import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpecialtyWomensComponent } from './specialty-womens.component';

describe('SpecialtyWomensComponent', () => {
  let component: SpecialtyWomensComponent;
  let fixture: ComponentFixture<SpecialtyWomensComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SpecialtyWomensComponent]
    });
    fixture = TestBed.createComponent(SpecialtyWomensComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
