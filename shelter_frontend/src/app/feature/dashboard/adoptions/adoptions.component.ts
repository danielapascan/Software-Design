import {
  Component,
  DestroyRef,
  EventEmitter,
  Input,
  Output,
  OnInit,
} from '@angular/core';
import { AdoptionModel } from '../../../shared/models/adoption.model';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { Router } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AdoptionService } from '../../../core/service/adoption/adoption.service';

@Component({
  selector: 'app-adoptions',
  templateUrl: './adoptions.component.html',
  styleUrl: './adoptions.component.css',
})
export class AdoptionsComponent implements OnInit {
  adoptions: AdoptionModel[] = [];
  sortBy: string = 'Date';
  currentPage: number = 0;
  totalPages: number = 0;
  pageSize: number = 0;
  totalElements: number = 0;
  totalPagesArray: number[] = [];

  constructor(
    private adoptionService: AdoptionService,
    private router: Router,
    private destroyRef: DestroyRef
  ) {}

  ngOnInit(): void {
    this.getAdoptions();
  }

  getAdoptions(): void {
    this.adoptionService
      .getAll(this.sortBy, this.currentPage)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          this.adoptions = response.adoptions;
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
    this.adoptionService
      .getAll(this.sortBy, page)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          this.adoptions = response.adoptions;
          this.currentPage = page;
        },
        error: (err) => console.log(err),
      });
  }

  deleteAdoption(adoptionId: string): void {
    this.adoptionService
      .deleteAdoption(adoptionId)
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (response) => {
          location.reload();
        },
        error: (err) => console.log(err),
      });
  }
}
