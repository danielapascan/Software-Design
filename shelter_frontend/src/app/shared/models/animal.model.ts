import { AnimalNeedsModel } from './animalNeeds.model';
import { AdoptionModel } from './adoption.model';
type Nullable<T> = T | null;
export interface AnimalModel {
  id: string;
  name: string;
  age: number;
  breed: string;
  gender: string;
  species: string;
  animalNeeds: AnimalNeedsModel;
}
