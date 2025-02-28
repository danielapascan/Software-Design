import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnimalSaveComponent } from './animal-save.component';

describe('AnimalSaveComponent', () => {
  let component: AnimalSaveComponent;
  let fixture: ComponentFixture<AnimalSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnimalSaveComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AnimalSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
