import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimalNeedsComponent } from './animal-needs.component';

describe('AnimalNeedsComponent', () => {
  let component: AnimalNeedsComponent;
  let fixture: ComponentFixture<AnimalNeedsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnimalNeedsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AnimalNeedsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
