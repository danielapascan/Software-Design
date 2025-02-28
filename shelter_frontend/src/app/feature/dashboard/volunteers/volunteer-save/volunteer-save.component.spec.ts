import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VolunteerSaveComponent } from './volunteer-save.component';

describe('VolunteerSaveComponent', () => {
  let component: VolunteerSaveComponent;
  let fixture: ComponentFixture<VolunteerSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VolunteerSaveComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VolunteerSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
