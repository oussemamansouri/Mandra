import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistreOwnerComponent } from './registre-owner.component';

describe('RegistreOwnerComponent', () => {
  let component: RegistreOwnerComponent;
  let fixture: ComponentFixture<RegistreOwnerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegistreOwnerComponent]
    });
    fixture = TestBed.createComponent(RegistreOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
