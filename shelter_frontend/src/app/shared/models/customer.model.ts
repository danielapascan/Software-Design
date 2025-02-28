import { DonationModel } from './donation.model';
import { AdoptionModel } from './adoption.model';
export interface CustomerModel {
  id: string;
  email: string;
  password: string;
  role: string;
  donations: DonationModel[];
  adoptions: AdoptionModel[];
}
