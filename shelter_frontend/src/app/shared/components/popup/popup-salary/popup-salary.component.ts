import { Component, DestroyRef, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { EmployeeModel } from '../../../models/employee.model';
import { CommonModule } from '@angular/common';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeService } from '../../../../core/service/employee/employee.service';

@Component({
  selector: 'app-popup-salary',
  templateUrl: './popup-salary.component.html',
  styleUrl: './popup-salary.component.css',
})
export class PopupSalaryComponent implements OnInit {
  employeeData: EmployeeModel = {
    id: '',
    email: '',
    password: '',
    role: 'EMPLOYEE',
    employmentDate: '',
    salary: 0,
  };

  constructor(
    public dialogRef: MatDialogRef<PopupSalaryComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { message: string; state: any },
    private route: ActivatedRoute,
    private destroyRef: DestroyRef,
    private router: Router,
    private employeeService: EmployeeService
  ) {}

  ngOnInit(): void {
    this.employeeData = this.data.state.state.employee;
  }

  onSubmit(employeeData: EmployeeModel): void {
    this.route.params
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe((response) => {
        this.employeeService.giveRaise(employeeData.id, employeeData).subscribe(
          (response) => {
            this.dialogRef.close();
            this.router.navigate(['/dashboard/employees']);
          },
          (error) => {
            console.error('Error saving raise:', error);
          }
        );
      });
  }

  closeDialog(): void {
    this.dialogRef.close();
  }
}
