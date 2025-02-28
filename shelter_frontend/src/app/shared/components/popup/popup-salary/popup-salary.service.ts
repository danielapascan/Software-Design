import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PopupSalaryComponent } from './popup-salary.component';
import { EmployeeModel } from '../../../models/employee.model';

@Injectable({
  providedIn: 'root',
})
export class PopupSalaryService {
  constructor(private dialog: MatDialog) {}

  openPopup(message: string, state: any) {
    this.dialog.open(PopupSalaryComponent, {
      data: { message: message, state: state },
    });
  }
}
