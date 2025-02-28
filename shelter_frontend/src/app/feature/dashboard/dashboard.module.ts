import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AnimalComponent } from './animal/animal.component';
import { AnimalCardComponent } from './animals/animal-card/animal-card.component';
import { AnimalsComponent } from './animals/animals.component';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { AnimalNeedsComponent } from './animals/animal-needs/animal-needs.component';
import { MatDialogModule } from '@angular/material/dialog';
import { AnimalSaveComponent } from './animals/animal-save/animal-save.component';
import { AnimalUpdateComponent } from './animals/animal-update/animal-update.component';
import { EmployeesComponent } from './employees/employees.component';
import { EmployeeSaveComponent } from './employees/employee-save/employee-save.component';
import { VolunteersComponent } from './volunteers/volunteers.component';
import { VolunteerSaveComponent } from './volunteers/volunteer-save/volunteer-save.component';
import { CustomersComponent } from './customers/customers.component';
import { CustomerSaveComponent } from './customers/customer-save/customer-save.component';
import { DonationsComponent } from './donations/donations.component';
import { DonationSaveComponent } from './donations/donation-save/donation-save.component';
import { DonationUpdateComponent } from './donations/donation-update/donation-update.component';
import { AdoptionsComponent } from './adoptions/adoptions.component';

@NgModule({
  declarations: [
    AnimalsComponent,
    AnimalComponent,
    AnimalCardComponent,
    AnimalNeedsComponent,
    AnimalSaveComponent,
    AnimalUpdateComponent,
    EmployeesComponent,
    EmployeeSaveComponent,
    VolunteersComponent,
    VolunteerSaveComponent,
    CustomersComponent,
    CustomerSaveComponent,
    DonationsComponent,
    DonationSaveComponent,
    DonationUpdateComponent,
    AdoptionsComponent,
  ],
  imports: [CommonModule, DashboardRoutingModule, FormsModule, MatDialogModule],
})
export class DashboardModule {}
