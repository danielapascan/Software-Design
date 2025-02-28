import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { AdoptionModel } from '../../../models/adoption.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-popup-adoptions',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './popup-adoptions.component.html',
  styleUrl: './popup-adoptions.component.css',
})
export class PopupAdoptionsComponent {
  constructor(
    public dialogRef: MatDialogRef<PopupAdoptionsComponent>,
    @Inject(MAT_DIALOG_DATA)
    public data: { message: string; adoptions: AdoptionModel[] }
  ) {}

  closeDialog(): void {
    this.dialogRef.close();
  }
}
