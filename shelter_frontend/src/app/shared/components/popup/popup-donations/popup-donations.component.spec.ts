import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupDonationsComponent } from './popup-donations.component';

describe('PopupDonationsComponent', () => {
  let component: PopupDonationsComponent;
  let fixture: ComponentFixture<PopupDonationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PopupDonationsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PopupDonationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
