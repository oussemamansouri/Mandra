import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiveClientsComponent } from './active-clients.component';

describe('ActiveClientsComponent', () => {
  let component: ActiveClientsComponent;
  let fixture: ComponentFixture<ActiveClientsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ActiveClientsComponent]
    });
    fixture = TestBed.createComponent(ActiveClientsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
