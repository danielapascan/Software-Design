import { Component, DestroyRef, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

import { CustomerService } from '../../../core/service/customer/customer.service';
import { CustomerModel } from '../../../shared/models/customer.model';
import { DonationModel } from '../../../shared/models/donation.model';
import { AdoptionModel } from '../../../shared/models/adoption.model';
import { PopupDonationsService } from '../../../shared/components/popup/popup-donations/popup-donations.service';
import { PopupAdoptionsService } from '../../../shared/components/popup/popup-adoptions/popup-adoptions.service';
import { PersonModel } from '../../../shared/models/person.model';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css',
})
export class CustomersComponent implements OnInit {
  customers: CustomerModel[] = [];
  donations: DonationModel[] = [];
  adoptions: AdoptionModel[] = [];
  sortBy: string = 'Email';
  currentPage: number = 0;
  totalPages: number = 0;
  pageSize: number = 0;
  totalElements: number = 0;
  totalPagesArray: number[] = [];
  loggedPerson: PersonModel = JSON.parse(
    localStorage.getItem('loggedPerson') || ''
  );

  constructor(
    private customerService: CustomerService,
    private router: Router,
    private destroyRef: DestroyRef,
    private popupDonationsService: PopupDonationsService,
    private popupAdoptionsService: PopupAdoptionsService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.getCustomers();
  }

  getCustomers(): void {
    this.customerService
      .getAll(this.sortBy, this.currentPage)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          this.customers = response.customers;
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
    this.customerService
      .getAll(this.sortBy, page)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          this.customers = response.customers;
          this.currentPage = page;
        },
        error: (err) => console.log(err),
      });
  }

  deleteCustomer(customerId: string): void {
    this.customerService
      .deleteCustomer(customerId)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          location.reload();
        },
        error: (err) => console.log(err),
      });
  }

  addCustomer(): void {
    this.router.navigate(['/dashboard/customers/save/']);
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
