import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiveOwnersComponent } from './active-owners.component';

describe('ActiveOwnersComponent', () => {
  let component: ActiveOwnersComponent;
  let fixture: ComponentFixture<ActiveOwnersComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ActiveOwnersComponent]
    });
    fixture = TestBed.createComponent(ActiveOwnersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
