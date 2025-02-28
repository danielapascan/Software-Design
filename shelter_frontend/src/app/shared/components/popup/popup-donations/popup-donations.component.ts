import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { DonationModel } from '../../../models/donation.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-popup-donations',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './popup-donations.component.html',
  styleUrl: './popup-donations.component.css',
})
export class PopupDonationsComponent {
  constructor(
    public dialogRef: MatDialogRef<PopupDonationsComponent>,
    @Inject(MAT_DIALOG_DATA)
    public data: { message: string; donations: DonationModel[] }
  ) {}

  closeDialog(): void {
    this.dialogRef.close();
  }
}
