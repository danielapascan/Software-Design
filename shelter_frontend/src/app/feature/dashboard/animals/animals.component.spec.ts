import {ComponentFixture, fakeAsync, TestBed, tick} from '@angular/core/testing';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http'
import {AnimalsComponent} from './animals.component';
import {AnimalCardComponent} from './animal-card/animal-card.component';
import {AnimalService} from '../../../core/service/animal/animal.service'
import {Router, RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {of} from 'rxjs';
import {RouterTestingModule} from '@angular/router/testing';
import any = jasmine.any;

describe('AnimalsComponent', () => {
  let component: AnimalsComponent;
  let fixture: ComponentFixture<AnimalsComponent>;
  let animalService: AnimalService;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule, CommonModule, FormsModule, RouterModule, ReactiveFormsModule],
      declarations: [AnimalsComponent, AnimalCardComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(AnimalsComponent);
    component = fixture.componentInstance;
    animalService = TestBed.inject(AnimalService);
    router = TestBed.inject(Router);
  });

  it('should create', () => {
    fixture.detectChanges();
    expect(component).toBeTruthy();
  });

  it('should logout when button is pressed', () => {
    // given
    fixture.detectChanges();
    const button: HTMLButtonElement = fixture.debugElement.nativeElement.querySelector('#logout-button');
    const buttonSpy = spyOn(component as any, 'logOut').and.callThrough();
    const routerSpy = spyOn(router as any, 'navigateByUrl');

    // when
    button.click();

    // then
    expect(buttonSpy).toHaveBeenCalled();
    expect(routerSpy).toHaveBeenCalledWith('/auth/login' as any);
  });

  it('should display all animals', () => {
    // given
    const animals = [{id: 'ID_Animal1', name: 'Wilson', age: 1, breed: 'Kelpie', gender: 'Male', species: 'Dog'}];
    const page = {
      totalPages: 1,
      totalElements: 1,
      number: 0,
      size: 10
    };
    const serviceSpy = spyOn(animalService as any, 'getAll').and.returnValue(of({animals, page}));

    // when
    fixture.detectChanges();
    // then
    expect(serviceSpy).toHaveBeenCalled();
    expect(fixture.debugElement.nativeElement.querySelector('app-animal-card')).toBeTruthy();
    expect(fixture.debugElement.nativeElement.querySelector('#empty-animals')).toBeFalsy();
  });

  it('should display no animals', () => {
    // given
    const serviceSpy = spyOn(animalService as any, 'getAll').and.returnValue(of([]));

    // when
    fixture.detectChanges();

    // then
    expect(serviceSpy).toHaveBeenCalled();
    expect(fixture.debugElement.nativeElement.querySelector('#empty-animals')).toBeTruthy();
    expect(fixture.debugElement.nativeElement.querySelector('app-animal-card')).toBeFalsy();
  });

  it('should navigate to Add animal page when the button is clicked', () => {
    fixture.detectChanges();
    const button: HTMLButtonElement = fixture.debugElement.nativeElement.querySelector('#animal-button');
    const buttonSpy = spyOn(component as any, 'addAnimal').and.callThrough();
    const routerSpy = spyOn(router as any, 'navigate');

    // when
    button.click();

    // then
    expect(buttonSpy).toHaveBeenCalled();
    expect(routerSpy).toHaveBeenCalledWith(['/dashboard/animals/save/'] as any);
  });

  it('should get animals paginated', () => {
    //given
    fixture.detectChanges();
    const button: HTMLButtonElement = fixture.debugElement.nativeElement.querySelector('#previous');
    const buttonSpy = spyOn(component as any, 'loadPage').and.callThrough();
    const serviceSpy = spyOn(animalService as any, 'getAll');

    //when
    button.click();

    //then
    expect(buttonSpy).toHaveBeenCalled();
    expect(serviceSpy).toHaveBeenCalled();
  });

});
