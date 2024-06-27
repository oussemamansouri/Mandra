import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistreComponent } from './registre.component';

describe('RegistreComponent', () => {
  let component: RegistreComponent;
  let fixture: ComponentFixture<RegistreComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegistreComponent]
    });
    fixture = TestBed.createComponent(RegistreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
