import { Component, DestroyRef, OnInit } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ActivatedRoute } from '@angular/router';
import { AnimalService } from '../../../core/service/animal/animal.service';
import { AnimalModel } from '../../../shared/models/animal.model';

@Component({
  selector: 'app-animal',
  templateUrl: './animal.component.html',
  styleUrl: './animal.component.css',
})
export class AnimalComponent implements OnInit {
  animal?: AnimalModel;
  animalId?: string;

  constructor(
    private route: ActivatedRoute,
    private animalService: AnimalService,
    private destroyRef: DestroyRef
  ) {}

  ngOnInit(): void {
    this.getAnimalById();
  }

  private getAnimalById(): void {
    this.route.params
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((response) => {
        this.animalId = response['id'];

        this.animalService
          .getById(this.animalId || '')
          .pipe(takeUntilDestroyed(this.destroyRef))
          .subscribe((response) => (this.animal = response));
      });
  }
}
