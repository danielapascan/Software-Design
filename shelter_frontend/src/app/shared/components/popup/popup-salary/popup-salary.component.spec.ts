import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupSalaryComponent } from './popup-salary.component';

describe('PopupSalaryComponent', () => {
  let component: PopupSalaryComponent;
  let fixture: ComponentFixture<PopupSalaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PopupSalaryComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(PopupSalaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
