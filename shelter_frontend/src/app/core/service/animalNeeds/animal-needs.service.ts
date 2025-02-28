import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AnimalNeedsModel } from '../../../shared/models/animalNeeds.model';

@Injectable({
  providedIn: 'root'
})
export class AnimalNeedsService {

  constructor(private http: HttpClient) {}

  getById(animalId: string): Observable<AnimalNeedsModel> {
    return this.http.get<AnimalNeedsModel>(`needs/all/${animalId}`);
  }

  updateLovePlusById(animalNeedsId: string, updatedNeeds: AnimalNeedsModel): Observable<any> {
    updatedNeeds.love += 1;
    return this.http.put<any>(`needs/all/${animalNeedsId}`, updatedNeeds);
  }

  updateLoveMinusById(animalNeedsId: string, updatedNeeds: AnimalNeedsModel): Observable<any> {
    updatedNeeds.love -= 1;
    return this.http.put<any>(`needs/all/${animalNeedsId}`, updatedNeeds);
  }

  updateCleanAnimalPlusById(animalNeedsId: string, updatedNeeds: AnimalNeedsModel): Observable<any> {
    updatedNeeds.cleanAnimal += 1;
    return this.http.put<any>(`needs/all/${animalNeedsId}`, updatedNeeds);
  }

  updateCleanAnimalMinusById(animalNeedsId: string, updatedNeeds: AnimalNeedsModel): Observable<any> {
    updatedNeeds.cleanAnimal -= 1;
    return this.http.put<any>(`needs/all/${animalNeedsId}`, updatedNeeds);
  }

  updateCleanCagePlusById(animalNeedsId: string, updatedNeeds: AnimalNeedsModel): Observable<any> {
    updatedNeeds.cleanCage += 1;
    return this.http.put<any>(`needs/all/${animalNeedsId}`, updatedNeeds);
  }

  updateCleanCageMinusById(animalNeedsId: string, updatedNeeds: AnimalNeedsModel): Observable<any> {
    updatedNeeds.cleanCage -= 1;
    return this.http.put<any>(`needs/all/${animalNeedsId}`, updatedNeeds);
  }

  updateWalkPlusById(animalNeedsId: string, updatedNeeds: AnimalNeedsModel): Observable<any> {
    updatedNeeds.walk += 1;
    return this.http.put<any>(`needs/all/${animalNeedsId}`, updatedNeeds);
  }

  updateWalkMinusById(animalNeedsId: string, updatedNeeds: AnimalNeedsModel): Observable<any> {
    updatedNeeds.walk -= 1;
    return this.http.put<any>(`needs/all/${animalNeedsId}`, updatedNeeds);
  }

  updateFoodPlusById(animalNeedsId: string, updatedNeeds: AnimalNeedsModel): Observable<any> {
    updatedNeeds.food += 1;
    return this.http.put<any>(`needs/all/${animalNeedsId}`, updatedNeeds);
  }

  updateFoodMinusById(animalNeedsId: string, updatedNeeds: AnimalNeedsModel): Observable<any> {
    updatedNeeds.food -= 1;
    return this.http.put<any>(`needs/all/${animalNeedsId}`, updatedNeeds);
  }

  saveAnimalNeeds(animalId: string, animalNeeds: AnimalNeedsModel): Observable<any> {
    return this.http.post<any>(`needs/all/${animalId}`, animalNeeds);
  }

}
