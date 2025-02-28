import { Component, DestroyRef, OnInit, Input } from '@angular/core';
import { EmployeeService } from '../../../core/service/employee/employee.service';
import { EmployeeModel } from '../../../shared/models/employee.model';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { Router } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';
import { PopupSalaryService } from '../../../shared/components/popup/popup-salary/popup-salary.service';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrl: './employees.component.css',
})
export class EmployeesComponent implements OnInit {
  employees: EmployeeModel[] = [];
  sortBy: string = 'Salary';
  currentPage: number = 0;
  totalPages: number = 0;
  pageSize: number = 0;
  totalElements: number = 0;
  totalPagesArray: number[] = [];

  constructor(
    private employeeService: EmployeeService,
    private router: Router,
    private destroyRef: DestroyRef,
    private popupSalaryService: PopupSalaryService
  ) {}

  ngOnInit(): void {
    this.getEmployees();
  }

  getEmployees(): void {
    this.employeeService
      .getAll(this.sortBy, this.currentPage)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          this.employees = response.employees;
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
    this.employeeService
      .getAll(this.sortBy, page)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          this.employees = response.employees;
          this.currentPage = page;
        },
        error: (err) => console.log(err),
      });
  }

  deleteEmployee(employeeId: string): void {
    this.employeeService
      .deleteEmployee(employeeId)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          location.reload();
        },
        error: (err) => console.log(err),
      });
  }

  addEmployee(): void {
    this.router.navigate(['/dashboard/employees/save/']);
  }

  giveRaise(employee: EmployeeModel): void {
    this.popupSalaryService.openPopup('', { state: { employee: employee } });
  }
}
