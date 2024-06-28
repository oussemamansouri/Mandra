import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistreClientComponent } from './registre-client.component';

describe('RegistreClientComponent', () => {
  let component: RegistreClientComponent;
  let fixture: ComponentFixture<RegistreClientComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegistreClientComponent]
    });
    fixture = TestBed.createComponent(RegistreClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
