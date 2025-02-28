import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {AnimalModel} from '../../../shared/models/animal.model';
import {HttpParams} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AnimalService {

  constructor(private http: HttpClient) {
  }

  getById(animalId: string): Observable<AnimalModel> {
    return this.http.get<AnimalModel>(`animals/all/${animalId}`);
  }

  getAll(searchBy: string = 'Dog', pageNumber: number): Observable<{ animals: AnimalModel[], page: any }> {
    return this.http.get<any>('animals/all', {
      params: {
        searchBy,
        pageNumber
      }
    }).pipe(
      map(response => ({
        animals: response.content,
        page: response
      }))
    );
  }

  saveAnimal(animal: AnimalModel): Observable<any> {
    return this.http.post<any>('animals/all', animal);
  }

  deleteAnimal(animalId: string): Observable<any> {
    return this.http.delete<any>(`animals/all/${animalId}`);
  }

  updateAnimal(animalId: string, animal: AnimalModel): Observable<any> {
    return this.http.put<any>(`animals/all/${animalId}`, animal);
  }

}
