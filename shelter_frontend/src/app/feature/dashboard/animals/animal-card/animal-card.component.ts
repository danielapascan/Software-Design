import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AnimalModel } from '../../../../shared/models/animal.model';
import { PersonModel } from '../../../../shared/models/person.model';
import { AnimalNeedsModel } from '../../../../shared/models/animalNeeds.model';
import { AdoptionModel } from '../../../../shared/models/adoption.model';
import { AdoptionService } from '../../../../core/service/adoption/adoption.service';
import { AnimalService } from '../../../../core/service/animal/animal.service';
import { PopupService } from '../../../../shared/components/popup/popup.service';

@Component({
  selector: 'app-animal-card',
  templateUrl: './animal-card.component.html',
  styleUrl: './animal-card.component.css',
})
export class AnimalCardComponent {
  @Input() animal!: AnimalModel;
  @Output() deleteAnimal: EventEmitter<string> = new EventEmitter<string>();
  @Output() updateAnimal: EventEmitter<AnimalModel> = new EventEmitter<AnimalModel>();
  @Output() updateAnimalNeeds: EventEmitter<string> = new EventEmitter<string>();
  @Output() details: EventEmitter<string> = new EventEmitter<string>();
  loggedPerson: PersonModel = JSON.parse(
    localStorage.getItem('loggedPerson') || '{}'
  );

  constructor(
    private router: Router,
    private adoptionService: AdoptionService,
    private popupService: PopupService,
    private animalService: AnimalService
  ) {}

  viewDetails(animalId: string): void {
    this.details.emit(animalId);
  }

  deleteAnimalPressed(animalId: string): void {
    this.deleteAnimal.emit(animalId);
  }

  updateAnimalPressed(animal: AnimalModel): void {
    this.updateAnimal.emit(animal);
  }

  updateAnimalNeedsPressed(animalId: string): void {
    this.updateAnimalNeeds.emit(animalId);
  }

  adoptAnimalPressed(animalId: string): void {
    const adoptionData: AdoptionModel = {
      id: '',
      localDate: new Date().toISOString(),
      personEntity: this.loggedPerson,
      animalEntity: this.animal,
    };

    this.adoptionService.saveAdoption(animalId, adoptionData).subscribe(
      (response) => {
        this.popupService.openPopup(
          'You just adopted a pet! Thank you!',
          'popup-container-yay'
        );
        this.router.navigate(['/dashboard/animals']);
        this.adoptionService.sendEmail(this.loggedPerson.email).subscribe(
        (response)=>{},
        (error) =>{
          console.log("Error sending email!");}
        );
      },
      (error) => {
        this.popupService.openPopup('This animal already has a home!','popup-container-default');
      }
    );
  }

}
