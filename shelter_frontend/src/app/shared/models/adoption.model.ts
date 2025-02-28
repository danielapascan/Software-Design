import { PersonModel } from './person.model';
import { AnimalModel } from './animal.model';
export interface AdoptionModel {
  id: string;
  personEntity: PersonModel;
  localDate: string;
  animalEntity: AnimalModel;
}
