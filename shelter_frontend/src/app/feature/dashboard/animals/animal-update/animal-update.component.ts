import { Component, DestroyRef, OnInit } from '@angular/core';
import { AnimalNeedsModel } from '../../../../shared/models/animalNeeds.model';
import { AnimalModel } from '../../../../shared/models/animal.model';
import { AdoptionModel } from '../../../../shared/models/adoption.model';
import { AnimalService } from '../../../../core/service/animal/animal.service';
import { AnimalNeedsService } from '../../../../core/service/animalNeeds/animal-needs.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-animal-update',
  templateUrl: './animal-update.component.html',
  styleUrl: './animal-update.component.css',
})
export class AnimalUpdateComponent implements OnInit {
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
    private route: ActivatedRoute,
    private destroyRef: DestroyRef,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.animalData = history.state.animal;
  }

  onSubmit(animalData: AnimalModel): void {
    this.route.params
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((response) => {
        this.animalData.id = response['id'];
        this.animalService.updateAnimal(animalData.id, animalData).subscribe(
          (response) => {
            this.router.navigate(['/dashboard/animals']);
          },
          (error) => {
            console.error('Error saving animal:', error);
          }
        );
      });
  }
}
