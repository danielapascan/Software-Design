import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DonationUpdateComponent } from './donation-update.component';

describe('DonationUpdateComponent', () => {
  let component: DonationUpdateComponent;
  let fixture: ComponentFixture<DonationUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DonationUpdateComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DonationUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
