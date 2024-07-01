import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpecialtyWomenComponent } from './specialty-women.component';

describe('SpecialtyWomenComponent', () => {
  let component: SpecialtyWomenComponent;
  let fixture: ComponentFixture<SpecialtyWomenComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SpecialtyWomenComponent]
    });
    fixture = TestBed.createComponent(SpecialtyWomenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
