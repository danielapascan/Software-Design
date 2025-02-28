import { Component, DestroyRef, OnInit } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AnimalNeedsModel } from '../../../../shared/models/animalNeeds.model';
import { AnimalNeedsService } from '../../../../core/service/animalNeeds/animal-needs.service';
import { MatDialog } from '@angular/material/dialog';
import { PopupService } from '../../../../shared/components/popup/popup.service';

@Component({
  selector: 'app-animal-needs',
  templateUrl: './animal-needs.component.html',
  styleUrl: './animal-needs.component.css',
})
export class AnimalNeedsComponent implements OnInit {
  animalNeeds?: AnimalNeedsModel;
  animalId?: string;
  animalNeedsId?: string;

  constructor(
    private route: ActivatedRoute,
    private animalNeedsService: AnimalNeedsService,
    private destroyRef: DestroyRef,
    private dialog: MatDialog,
    private popupService: PopupService
  ) {}

  ngOnInit(): void {
    this.getAnimalNeedsById();
  }

  private getAnimalNeedsById(): void {
    this.route.params
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((response) => {
        this.animalId = response['id'];

        this.animalNeedsService
          .getById(this.animalId || '')
          .pipe(takeUntilDestroyed(this.destroyRef))
          .subscribe((response) => {
            this.animalNeeds = response;
            this.animalNeedsId = response.id;
          });
      });
  }

  plusAnimalLove(): void {
    if (this.animalNeeds && this.animalNeeds.love === 10) {
      this.popupService.openPopup(
        'It looks like the animal is very loved!',
        'popup-container-full-love'
      );
    } else {
      if (this.animalNeedsId && this.animalNeeds) {
        this.animalNeedsService
          .updateLovePlusById(this.animalNeedsId, this.animalNeeds)
          .pipe(takeUntilDestroyed(this.destroyRef))
          .subscribe(
            () => {},
            (error) => {
              console.error(
                'A apărut o eroare la actualizarea nevoilor animalului:',
                error
              );
            }
          );
      }
    }
  }

  minusAnimalLove(): void {
    if (this.animalNeeds && this.animalNeeds.love === 0) {
      this.popupService.openPopup(
        'Please give some love to the animal!',
        'popup-container-empty-love'
      );
    } else {
      if (this.animalNeedsId && this.animalNeeds) {
        this.animalNeedsService
          .updateLoveMinusById(this.animalNeedsId, this.animalNeeds)
          .pipe(takeUntilDestroyed(this.destroyRef))
          .subscribe(
            () => {},
            (error) => {
              console.error(
                'A apărut o eroare la actualizarea nevoilor animalului:',
                error
              );
            }
          );
      }
    }
  }

  plusCleanAnimal(): void {
    if (this.animalNeeds && this.animalNeeds.cleanAnimal === 10) {
      this.popupService.openPopup(
        'Wow the animal is really clean!',
        'popup-container-full-love'
      );
    } else {
      if (this.animalNeedsId && this.animalNeeds) {
        this.animalNeedsService
          .updateCleanAnimalPlusById(this.animalNeedsId, this.animalNeeds)
          .pipe(takeUntilDestroyed(this.destroyRef))
          .subscribe(
            () => {},
            (error) => {
              console.error(
                'A apărut o eroare la actualizarea nevoilor animalului:',
                error
              );
            }
          );
      }
    }
  }

  minusCleanAnimal(): void {
    if (this.animalNeeds && this.animalNeeds.cleanAnimal === 0) {
      this.popupService.openPopup(
        'Please give a bath to this animal!',
        'popup-container-empty-love'
      );
    } else {
      if (this.animalNeedsId && this.animalNeeds) {
        this.animalNeedsService
          .updateCleanAnimalMinusById(this.animalNeedsId, this.animalNeeds)
          .pipe(takeUntilDestroyed(this.destroyRef))
          .subscribe(
            () => {},
            (error) => {
              console.error(
                'A apărut o eroare la actualizarea nevoilor animalului:',
                error
              );
            }
          );
      }
    }
  }

  plusCleanCage(): void {
    if (this.animalNeeds && this.animalNeeds.cleanCage === 10) {
      this.popupService.openPopup(
        "Wow the animal's cage is really clean!",
        'popup-container-full-love'
      );
    } else {
      if (this.animalNeedsId && this.animalNeeds) {
        this.animalNeedsService
          .updateCleanCagePlusById(this.animalNeedsId, this.animalNeeds)
          .pipe(takeUntilDestroyed(this.destroyRef))
          .subscribe(
            () => {},
            (error) => {
              console.error(
                'A apărut o eroare la actualizarea nevoilor animalului:',
                error
              );
            }
          );
      }
    }
  }

  minusCleanCage(): void {
    if (this.animalNeeds && this.animalNeeds.cleanCage === 0) {
      this.popupService.openPopup(
        "Please clean this animal's cage!",
        'popup-container-empty-love'
      );
    } else {
      if (this.animalNeedsId && this.animalNeeds) {
        this.animalNeedsService
          .updateCleanCageMinusById(this.animalNeedsId, this.animalNeeds)
          .pipe(takeUntilDestroyed(this.destroyRef))
          .subscribe(
            () => {},
            (error) => {
              console.error(
                'A apărut o eroare la actualizarea nevoilor animalului:',
                error
              );
            }
          );
      }
    }
  }

  plusWalk(): void {
    if (this.animalNeeds && this.animalNeeds.walk === 10) {
      this.popupService.openPopup(
        'Wow the animal is really healthy!',
        'popup-container-full-love'
      );
    } else {
      if (this.animalNeedsId && this.animalNeeds) {
        this.animalNeedsService
          .updateWalkPlusById(this.animalNeedsId, this.animalNeeds)
          .pipe(takeUntilDestroyed(this.destroyRef))
          .subscribe(
            () => {},
            (error) => {
              console.error(
                'A apărut o eroare la actualizarea nevoilor animalului:',
                error
              );
            }
          );
      }
    }
  }

  minusWalk(): void {
    if (this.animalNeeds && this.animalNeeds.walk === 0) {
      this.popupService.openPopup(
        'Please take a walk with this animal!',
        'popup-container-empty-love'
      );
    } else {
      if (this.animalNeedsId && this.animalNeeds) {
        this.animalNeedsService
          .updateWalkMinusById(this.animalNeedsId, this.animalNeeds)
          .pipe(takeUntilDestroyed(this.destroyRef))
          .subscribe(
            () => {},
            (error) => {
              console.error(
                'A apărut o eroare la actualizarea nevoilor animalului:',
                error
              );
            }
          );
      }
    }
  }

  plusFood(): void {
    if (this.animalNeeds && this.animalNeeds.food === 10) {
      this.popupService.openPopup(
        'Wow the animal had a lot of food!',
        'popup-container-full-love'
      );
    } else {
      if (this.animalNeedsId && this.animalNeeds) {
        this.animalNeedsService
          .updateFoodPlusById(this.animalNeedsId, this.animalNeeds)
          .pipe(takeUntilDestroyed(this.destroyRef))
          .subscribe(
            () => {},
            (error) => {
              console.error(
                'A apărut o eroare la actualizarea nevoilor animalului:',
                error
              );
            }
          );
      }
    }
  }

  minusFood(): void {
    if (this.animalNeeds && this.animalNeeds.food === 0) {
      this.popupService.openPopup(
        'Please give this animal a snack!',
        'popup-container-empty-love'
      );
    } else {
      if (this.animalNeedsId && this.animalNeeds) {
        this.animalNeedsService
          .updateFoodMinusById(this.animalNeedsId, this.animalNeeds)
          .pipe(takeUntilDestroyed(this.destroyRef))
          .subscribe(
            () => {},
            (error) => {
              console.error(
                'A apărut o eroare la actualizarea nevoilor animalului:',
                error
              );
            }
          );
      }
    }
  }
}
