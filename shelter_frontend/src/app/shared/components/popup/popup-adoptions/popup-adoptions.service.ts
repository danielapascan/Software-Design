import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PopupAdoptionsComponent } from './popup-adoptions.component';
import { AdoptionModel } from '../../../models/adoption.model';

@Injectable({
  providedIn: 'root',
})
export class PopupAdoptionsService {
  constructor(private dialog: MatDialog) {}

  openPopup(message: string, adoptions: AdoptionModel[]) {
    this.dialog.open(PopupAdoptionsComponent, {
      data: { message: message, adoptions: adoptions },
    });
  }
}
