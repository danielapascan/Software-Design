import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { AnimalCardComponent } from './animal-card.component';
import { AnimalService } from '../../../../core/service/animal/animal.service'
import { Router, RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { of } from 'rxjs';
import { RouterTestingModule } from '@angular/router/testing';
import {NO_ERRORS_SCHEMA} from "@angular/core";

describe('AnimalCardComponent', () => {
  let component: AnimalCardComponent;
  let fixture: ComponentFixture<AnimalCardComponent>;
  let animalService: AnimalService;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ HttpClientModule, CommonModule, FormsModule, RouterModule, ReactiveFormsModule],
      declarations: [ AnimalCardComponent ],
      schemas: [NO_ERRORS_SCHEMA]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnimalCardComponent);
    component = fixture.componentInstance;
    animalService = TestBed.inject(AnimalService);
    router = TestBed.inject(Router);
  });

  it('should create', () => {
    fixture.detectChanges();
    expect(component).toBeTruthy();
  });
//
//   it('should get animal\'s information when button is pressed', () => {
//       // given
//       fixture.detectChanges();
//       const button: HTMLButtonElement = fixture.debugElement.nativeElement.querySelector('#btn-details');
//       const buttonSpy = spyOn(component as any, 'viewDetails').and.callThrough();
//       const serviceSpy = spyOn(animalService as any, 'getById');
//
//       // when
//       button.click();
//
//       // then
//       expect(buttonSpy).toHaveBeenCalled();
//       expect(serviceSpy).toHaveBeenCalled();
//     });

});
