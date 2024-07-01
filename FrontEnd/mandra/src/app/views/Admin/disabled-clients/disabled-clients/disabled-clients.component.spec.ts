import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisabledClientsComponent } from './disabled-clients.component';

describe('DisabledClientsComponent', () => {
  let component: DisabledClientsComponent;
  let fixture: ComponentFixture<DisabledClientsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DisabledClientsComponent]
    });
    fixture = TestBed.createComponent(DisabledClientsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
