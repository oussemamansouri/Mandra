import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientLayouteComponent } from './client-layoute.component';

describe('ClientLayouteComponent', () => {
  let component: ClientLayouteComponent;
  let fixture: ComponentFixture<ClientLayouteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClientLayouteComponent]
    });
    fixture = TestBed.createComponent(ClientLayouteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
