import { Component, DestroyRef, OnInit } from '@angular/core';
import { DonationModel } from '../../../../shared/models/donation.model';
import { DonationService } from '../../../../core/service/donation/donation.service';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonModel } from '../../../../shared/models/person.model';

@Component({
  selector: 'app-donation-update',
  templateUrl: './donation-update.component.html',
  styleUrl: './donation-update.component.css',
})
export class DonationUpdateComponent implements OnInit {
  loggedPerson: PersonModel = JSON.parse(
    localStorage.getItem('loggedPerson') || ''
  );

  donationData: DonationModel = {
    id: '',
    personEntity: this.loggedPerson,
    amount: 0,
    cause: '',
    localDate: '',
    description: '',
  };

  constructor(
    private donationService: DonationService,
    private route: ActivatedRoute,
    private destroyRef: DestroyRef,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.donationData = history.state.donation;
  }

  onSubmit(donationData: DonationModel): void {
    this.route.params
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((response) => {
        this.donationData.id = response['id'];
        this.donationService
          .updateDonation(donationData.id, donationData)
          .subscribe(
            (response) => {
              this.router.navigate(['/dashboard/donations']);
            },
            (error) => {
              console.error('Error saving donation:', error);
            }
          );
      });
  }
}
