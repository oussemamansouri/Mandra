import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FrontLayouteComponent } from './front-layoute.component';

describe('FrontLayouteComponent', () => {
  let component: FrontLayouteComponent;
  let fixture: ComponentFixture<FrontLayouteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FrontLayouteComponent]
    });
    fixture = TestBed.createComponent(FrontLayouteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
