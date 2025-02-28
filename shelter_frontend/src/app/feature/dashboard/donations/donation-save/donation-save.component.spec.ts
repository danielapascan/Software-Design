import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DonationSaveComponent } from './donation-save.component';

describe('DonationSaveComponent', () => {
  let component: DonationSaveComponent;
  let fixture: ComponentFixture<DonationSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DonationSaveComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DonationSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
