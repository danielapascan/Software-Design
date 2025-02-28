import {
  Component,
  DestroyRef,
  EventEmitter,
  Input,
  Output,
  OnInit,
} from '@angular/core';
import { DonationModel } from '../../../shared/models/donation.model';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { Router } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DonationService } from '../../../core/service/donation/donation.service';
import { PersonModel } from '../../../shared/models/person.model';

@Component({
  selector: 'app-donations',
  templateUrl: './donations.component.html',
  styleUrl: './donations.component.css',
})
export class DonationsComponent implements OnInit {
  @Input() donation!: DonationModel;
  donations: DonationModel[] = [];
  sortBy: string = 'Amount';
  currentPage: number = 0;
  totalPages: number = 0;
  pageSize: number = 0;
  totalElements: number = 0;
  totalPagesArray: number[] = [];
  loggedPerson: PersonModel = JSON.parse(
    localStorage.getItem('loggedPerson') || ''
  );

  constructor(
    private donationService: DonationService,
    private router: Router,
    private destroyRef: DestroyRef
  ) {}

  ngOnInit(): void {
    this.getDonations();
  }

  getDonations(): void {
    this.donationService
      .getAll(this.sortBy, this.currentPage)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          this.donations = response.donations;
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
    this.donationService
      .getAll(this.sortBy, page)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          this.donations = response.donations;
          this.currentPage = page;
        },
        error: (err) => console.log(err),
      });
  }

  addDonation() {
    this.router.navigate(['/dashboard/donations/save']);
  }

  editDonation(donation: DonationModel): void {
    this.donation = donation;
    this.router.navigate(['/dashboard/donations/update/' + donation.id], {
      state: { donation: this.donation },
    });
  }

  deleteDonation(donationId: string): void {
    this.donationService
      .deleteDonation(donationId)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          location.reload();
        },
        error: (err) => console.log(err),
      });
  }
}
