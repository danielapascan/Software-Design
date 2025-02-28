import { Component, DestroyRef, OnInit } from '@angular/core';
import { VolunteerService } from '../../../core/service/volunteer/volunteer.service';
import { VolunteerModel } from '../../../shared/models/volunteer.model';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { Router } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';
@Component({
  selector: 'app-volunteers',
  templateUrl: './volunteers.component.html',
  styleUrl: './volunteers.component.css',
})
export class VolunteersComponent {
  volunteers: VolunteerModel[] = [];
  sortBy: string = 'Email';
  currentPage: number = 0;
  totalPages: number = 0;
  pageSize: number = 0;
  totalElements: number = 0;
  totalPagesArray: number[] = [];

  constructor(
    private volunteerService: VolunteerService,
    private router: Router,
    private destroyRef: DestroyRef
  ) {}

  ngOnInit(): void {
    this.getVolunteers();
  }

  getVolunteers(): void {
    this.volunteerService
      .getAll(this.sortBy, this.currentPage)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          this.volunteers = response.volunteers;
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
    this.volunteerService
      .getAll(this.sortBy, page)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          this.volunteers = response.volunteers;
          this.currentPage = page;
        },
        error: (err) => console.log(err),
      });
  }

  deleteVolunteer(volunteerId: string): void {
    this.volunteerService
      .deleteVolunteer(volunteerId)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          location.reload();
        },
        error: (err) => console.log(err),
      });
  }

  addVolunteer(): void {
    this.router.navigate(['/dashboard/volunteers/save/']);
  }
}
