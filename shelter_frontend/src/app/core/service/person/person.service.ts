import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PersonModel } from '../../../shared/models/person.model';


@Injectable({
  providedIn: 'root'
})
export class PersonService {

  constructor(private http: HttpClient) {}

  getInfo(): Observable<PersonModel> {
    return this.http.get<PersonModel>('persons/info');
  }

}
