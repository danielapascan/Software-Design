import { Injectable } from '@angular/core';
import { DonationModel } from '../../../shared/models/donation.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DonationService {
  constructor(private http: HttpClient) { }

  getAll(sortBy: string = 'Amount', pageNumber:number): Observable<{ donations: DonationModel[], page: any }> {
    return this.http.get<any>('donations/all',{
      params: {
        sortBy,
        pageNumber
      }
      }).pipe(
        map(response => ({
          donations: response.content,
          page: response
        }))
      );
  }

  saveDonation(donation: DonationModel): Observable<any> {
    const customerId = donation.personEntity.id;
    return this.http.post<any>(`donations/all/${customerId}`,donation);
  }

  updateDonation(donationId: string,donation: DonationModel): Observable<any> {
    return this.http.put<any>(`donations/all/${donationId}`, donation);
  }

  deleteDonation(donationId: string): Observable<any> {
    return this.http.delete<any>(`donations/all/${donationId}`);
  }
}
