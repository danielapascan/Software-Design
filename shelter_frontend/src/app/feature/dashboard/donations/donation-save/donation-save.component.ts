import { Component } from '@angular/core';
import { DonationService } from '../../../../core/service/donation/donation.service';
import { Router } from '@angular/router';
import { CustomerModel } from '../../../../shared/models/customer.model';
import { PopupService } from '../../../../shared/components/popup/popup.service';
import { DonationModel } from '../../../../shared/models/donation.model';
import { PersonModel } from '../../../../shared/models/person.model';

@Component({
  selector: 'app-donation-save',
  templateUrl: './donation-save.component.html',
  styleUrl: './donation-save.component.css',
})
export class DonationSaveComponent {
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
    private router: Router,
    private popupService: PopupService
  ) {}

  onSubmit(donationData: DonationModel): void {
    console.log(donationData);
    this.donationService.saveDonation(donationData).subscribe(
      (response) => {
        this.popupService.openPopup(
          'Thank you so much for your donation!',
          'popup-container-yay'
        );
        this.router.navigate(['/dashboard/animals']);
      },
      (error) => {
        this.popupService.openPopup('The data you introduced is not valid!');
      }
    );
  }
}
