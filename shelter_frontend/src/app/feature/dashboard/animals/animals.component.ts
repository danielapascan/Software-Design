import { Component, DestroyRef, OnInit } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { Router } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AnimalService } from '../../../core/service/animal/animal.service';
import { AnimalModel } from '../../../shared/models/animal.model';
import { PersonModel } from '../../../shared/models/person.model';
import { PopupDonationsService } from '../../../shared/components/popup/popup-donations/popup-donations.service';
import { PopupAdoptionsService } from '../../../shared/components/popup/popup-adoptions/popup-adoptions.service';
import { CustomerService } from '../../../core/service/customer/customer.service';
import { DonationModel } from '../../../shared/models/donation.model';
import { AdoptionModel } from '../../../shared/models/adoption.model';

@Component({
  selector: 'app-animals',
  templateUrl: './animals.component.html',
  styleUrl: './animals.component.css',
})
export class AnimalsComponent implements OnInit {
  animals: AnimalModel[] = [];
  searchBy: string = 'Dog';
  donations: DonationModel[] = [];
  adoptions: AdoptionModel[] = [];
  loggedPerson: PersonModel = JSON.parse(
    localStorage.getItem('loggedPerson') || '{}'
  );
  currentPage: number = 0;
  totalPages: number = 0;
  pageSize: number = 0;
  totalElements: number = 0;
  totalPagesArray: number[] = [];

  constructor(
    private animalService: AnimalService,
    private router: Router,
    private destroyRef: DestroyRef,
    private customerService: CustomerService,
    private popupDonationsService: PopupDonationsService,
    private popupAdoptionsService: PopupAdoptionsService
  ) {}

  ngOnInit(): void {
    this.getAnimals();
  }

  deleteAnimalCalled(animalId: string): void {
    this.animalService
      .deleteAnimal(animalId)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          location.reload();
        },
        error: (err) => console.log(err),
      });
  }

  updateAnimalCalled(animal: AnimalModel): void {
    this.router.navigate(['/dashboard/animals/update/' + animal.id], {
          state: { animal: animal },
        });
  }

  updateAnimalNeedsCalled(animalId: string): void {
    this.router.navigate(['/dashboard/needs/' + animalId]);
  }

  viewDetails(animalId: string): void {
    this.router.navigate(['/dashboard/animal/' + animalId]);
  }


  logOut(): void {
    this.clearCookies();
    localStorage.removeItem('loggedUser');
    this.router.navigateByUrl('/auth/login');
  }

  getAnimals(): void {
    this.animalService
      .getAll(this.searchBy, this.currentPage)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          this.animals = response.animals;
          this.totalPages = response.page.totalPages;
          this.totalElements = response.page.totalElements;
          this.pageSize = response.page.size;
          this.totalPagesArray = Array.from(
            { length: this.totalPages },
            (_, index) => index
          );
        },
        error: (err) => console.log(err),
      });
  }

  loadPage(page: number): void {
    this.animalService
      .getAll(this.searchBy, page)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          this.animals = response.animals;
          this.currentPage = page;
        },
        error: (err) => console.log(err),
      });
  }

  getAnimalsBySpecies(): void {
    this.animalService
      .getAll(this.searchBy, this.currentPage)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          this.animals = response.animals;
        },
        error: (err) => console.log(err),
      });
  }

  addAnimal(): void {
    this.router.navigate(['/dashboard/animals/save/']);
  }

  goToEmployees() {
    this.router.navigate(['/dashboard/employees/']);
  }

  goToVolunteers() {
    this.router.navigate(['/dashboard/volunteers/']);
  }

  goToCustomers() {
    this.router.navigate(['/dashboard/customers/']);
  }

  goToDonations() {
    this.router.navigate(['/dashboard/donations/']);
  }

  goToAdoptions() {
    this.router.navigate(['/dashboard/adoptions/']);
  }

  private clearCookies(): void {
    const cookies = document.cookie.split(';');
    for (let i = 0; i < cookies.length; i++) {
      const cookie = cookies[i];
      const equalPos = cookie.indexOf('=');
      const name = equalPos > -1 ? cookie.slice(0, equalPos) : cookie;
      document.cookie =
        name + '=;expires=Thu, 01 Jan 1970 00:00:00 GMT;path=/;';
    }
  }

  makeDonation() {
    this.router.navigate(['/dashboard/donations/save']);
  }

  seeDonations(customerId: string): void {
    this.customerService
      .seeDonations(customerId)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          this.donations = response.donations;
          this.popupDonationsService.openPopup('', this.donations);
        },
        error: (err) => console.log(err),
      });
  }

  seeAdoptions(customerId: string): void {
    this.customerService
      .seeAdoptions(customerId)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          this.adoptions = response.adoptions;
          this.popupAdoptionsService.openPopup('', this.adoptions);
        },
        error: (err) => console.log(err),
      });
  }
}
