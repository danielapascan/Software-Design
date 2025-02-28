import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupAdoptionsComponent } from './popup-adoptions.component';

describe('PopupAdoptionsComponent', () => {
  let component: PopupAdoptionsComponent;
  let fixture: ComponentFixture<PopupAdoptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PopupAdoptionsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PopupAdoptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
