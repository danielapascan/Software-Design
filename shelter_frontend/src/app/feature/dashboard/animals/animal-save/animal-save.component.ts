import { Component } from '@angular/core';
import { AnimalNeedsModel } from '../../../../shared/models/animalNeeds.model';
import { AnimalModel } from '../../../../shared/models/animal.model';
import { AdoptionModel } from '../../../../shared/models/adoption.model';
import { AnimalService } from '../../../../core/service/animal/animal.service';
import { AnimalNeedsService } from '../../../../core/service/animalNeeds/animal-needs.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-animal-save',
  templateUrl: './animal-save.component.html',
  styleUrl: './animal-save.component.css',
})
export class AnimalSaveComponent {
  animalNeedsData: AnimalNeedsModel = {
    id: '',
    love: 0,
    cleanCage: 0,
    cleanAnimal: 0,
    walk: 0,
    food: 0,
  };

  animalData: AnimalModel = {
    id: '',
    name: '',
    age: 0,
    breed: '',
    gender: '',
    species: '',
    animalNeeds: this.animalNeedsData,
  };

  constructor(
    private animalService: AnimalService,
    private animalNeedsService: AnimalNeedsService,
    private router: Router
  ) {}

  onSubmit(animalData: AnimalModel): void {
    this.animalService.saveAnimal(animalData).subscribe(
      (response) => {
        const animalId = response.id;
        this.animalNeedsService
          .saveAnimalNeeds(animalId, this.animalNeedsData)
          .subscribe(
            (response) => {
              this.router.navigate(['/dashboard/animals']);
            },
            (error) => {
              console.error('Error saving animal needs:', error);
            }
          );
      },
      (error) => {
        console.error('Error saving animal:', error);
      }
    );
  }
}
