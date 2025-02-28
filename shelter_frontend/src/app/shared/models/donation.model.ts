import { PersonModel } from './person.model';
export interface DonationModel {
  id: string;
  personEntity: PersonModel;
  amount: number;
  cause: string;
  localDate: string;
  description: string;
}
