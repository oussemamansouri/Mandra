import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GastronomicSpecialtiesComponent } from './gastronomic-specialties.component';

describe('GastronomicSpecialtiesComponent', () => {
  let component: GastronomicSpecialtiesComponent;
  let fixture: ComponentFixture<GastronomicSpecialtiesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GastronomicSpecialtiesComponent]
    });
    fixture = TestBed.createComponent(GastronomicSpecialtiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
