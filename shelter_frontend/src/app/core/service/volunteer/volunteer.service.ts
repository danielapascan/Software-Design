import { Injectable } from '@angular/core';
import { VolunteerModel } from '../../../shared/models/volunteer.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class VolunteerService {

  constructor(private http: HttpClient) { }

  getAll(sortBy: string = 'Email', pageNumber: number): Observable<{ volunteers: VolunteerModel[], page: any }> {
    return this.http.get<any>('volunteers/all',{
      params: {
        sortBy,
        pageNumber
      }
      }).pipe(
      map(response => ({
        volunteers: response.content,
        page: response
      }))
    );
  }

  deleteVolunteer(volunteerId: string): Observable<any> {
    return this.http.delete<any>(`volunteers/all/${volunteerId}`);
  }

  saveVolunteer(volunteer: VolunteerModel): Observable<any> {
    return this.http.post<any>('volunteers/all', volunteer);
  }

}
