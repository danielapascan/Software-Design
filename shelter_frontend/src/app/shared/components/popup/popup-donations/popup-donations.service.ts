import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PopupDonationsComponent } from './popup-donations.component';
import { DonationModel } from '../../../models/donation.model';

@Injectable({
  providedIn: 'root',
})
export class PopupDonationsService {
  constructor(private dialog: MatDialog) {}

  openPopup(message: string, donations: DonationModel[]) {
    this.dialog.open(PopupDonationsComponent, {
      data: { message: message, donations: donations },
    });
  }
}
