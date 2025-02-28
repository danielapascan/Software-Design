import { Injectable } from '@angular/core';
import { CustomerModel } from '../../../shared/models/customer.model';
import { DonationModel } from '../../../shared/models/donation.model';
import { AdoptionModel } from '../../../shared/models/adoption.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

constructor(private http: HttpClient) { }

  getAll(sortBy: string = 'Email',pageNumber:number): Observable<{ customers: CustomerModel[], page: any }> {
    return this.http.get<any>('customers/all',{
      params: {
         sortBy,
         pageNumber
      }
      }).pipe(
      map(response => ({
        customers: response.content,
        page: response
      }))
    );
  }

  deleteCustomer(customerId: string): Observable<any> {
    return this.http.delete<any>(`customers/all/${customerId}`);
  }

  saveCustomer(customer: CustomerModel): Observable<any> {
    return this.http.post<any>('customers/all', customer);
  }

  seeDonations(customerId: string): Observable<{ donations: DonationModel[] }> {
    return this.http.get<any>(`customers/all/${customerId}`).pipe(
      map(response => {
        return {
          donations: response
        };
      })
    );
  }

  seeAdoptions(customerId: string): Observable<{ adoptions: AdoptionModel[] }> {
    return this.http.get<any>(`customers/all/adoptions/${customerId}`).pipe(
      map(response => {
        return {
          adoptions: response
        };
      })
    );
  }

}
