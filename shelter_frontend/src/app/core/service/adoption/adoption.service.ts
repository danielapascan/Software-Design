import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AdoptionModel } from '../../../shared/models/adoption.model';
import { MailModel } from '../../../shared/models/mail.model';
import { HttpParams } from '@angular/common/http';
import { map ,switchMap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AdoptionService {

  mailData: MailModel = {
      from: 'admin@gmail.com',
      to: '',
      subject: 'Bring Love Home - Adopt a Pet Today!',
      body: 'Thank you for opening your heart and home to your new pet friend. Your decision to adopt has not only changed his life but has also made a significant impact on all of us at Loving Hearts Rescue. Thank you once again for making a difference. Wishing you and your pet a lifetime of love, joy, and unforgettable moments.'
    };

  constructor(private http: HttpClient) { }

  getAll(sortBy: string = 'Date', pageNumber:number): Observable<{ adoptions: AdoptionModel[], page: any }> {
    return this.http.get<any>('adoptions/all',{
      params: {
        sortBy,
        pageNumber
      }
      }).pipe(
        map(response => ({
          adoptions: response.content,
          page: response
        }))
    );
  }

  saveAdoption( animalId: string, adoption: AdoptionModel): Observable<any> {
   return this.http.post<any>(`adoptions/all/${adoption.personEntity.id}/${animalId}`, adoption);
  }

  sendEmail(email: string): Observable<any> {
    this.mailData.to = email;
    console.log(this.mailData);
    return this.http.post<any>('mail/v1/async', this.mailData);
  }

  deleteAdoption(adoptionId: string): Observable<any> {
    return this.http.delete<any>(`adoptions/all/${adoptionId}`);
  }

}
