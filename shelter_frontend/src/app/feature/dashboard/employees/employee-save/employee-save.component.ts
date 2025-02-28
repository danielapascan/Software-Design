import { Component } from '@angular/core';
import { EmployeeService } from '../../../../core/service/employee/employee.service';
import { Router } from '@angular/router';
import { EmployeeModel } from '../../../../shared/models/employee.model';
import { PopupService } from '../../../../shared/components/popup/popup.service';

@Component({
  selector: 'app-employee-save',
  templateUrl: './employee-save.component.html',
  styleUrl: './employee-save.component.css',
})
export class EmployeeSaveComponent {
  employeeData: EmployeeModel = {
    id: '',
    email: '',
    password: '',
    role: 'EMPLOYEE',
    employmentDate: '',
    salary: 0,
  };

  constructor(
    private employeeService: EmployeeService,
    private router: Router,
    private popupService: PopupService
  ) {}

  onSubmit(employeeData: EmployeeModel): void {
    this.employeeService.saveEmployee(employeeData).subscribe(
      (response) => {
        this.router.navigate(['/dashboard/employees']);
      },
      (error) => {
        this.popupService.openPopup('The data you introduced is not valid!');
      }
    );
  }
}
