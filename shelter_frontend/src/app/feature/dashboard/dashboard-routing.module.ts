import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { hasRole } from '../../core/guard/authorization.guard';
import { InvalidAccessComponent } from '../../shared/components/invalid-access/invalid-access.component';
import { NotFoundComponent } from '../../shared/components/not-found/not-found.component';
import { AnimalComponent } from './animal/animal.component';
import { AnimalsComponent } from './animals/animals.component';
import { AnimalNeedsComponent } from './animals/animal-needs/animal-needs.component';
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

export const routes: Routes = [
  {
    path: 'animals',
    component: AnimalsComponent,
    canActivate: [hasRole],
    data: {
      requiredRoles: ['ADMIN', 'EMPLOYEE', 'VOLUNTEER', 'CUSTOMER'],
    },
  },
  {
    path: 'animals/save',
    component: AnimalSaveComponent,
    canActivate: [hasRole],
    data: {
      requiredRoles: ['EMPLOYEE', 'VOLUNTEER', 'CUSTOMER'],
    },
  },
  {
    path: 'animals/update/:id',
    component: AnimalUpdateComponent,
    canActivate: [hasRole],
    data: {
      requiredRoles: ['EMPLOYEE', 'VOLUNTEER'],
    },
  },
  {
    path: 'animal/:id',
    component: AnimalComponent,
    canActivate: [hasRole],
    data: {
      requiredRoles: ['ADMIN', 'EMPLOYEE', 'VOLUNTEER', 'CUSTOMER'],
    },
  },
  {
    path: 'needs/:id',
    component: AnimalNeedsComponent,
    canActivate: [hasRole],
    data: {
      requiredRoles: ['EMPLOYEE', 'VOLUNTEER'],
    },
  },
  {
    path: 'needs/all/:id',
    component: AnimalNeedsComponent,
    canActivate: [hasRole],
    data: {
      requiredRoles: ['EMPLOYEE', 'VOLUNTEER'],
    },
  },
  {
    path: 'employees',
    component: EmployeesComponent,
    canActivate: [hasRole],
    data: {
      requiredRoles: ['ADMIN'],
    },
  },
  {
    path: 'employees/save',
    component: EmployeeSaveComponent,
    canActivate: [hasRole],
    data: {
      requiredRoles: ['ADMIN'],
    },
  },
  {
    path: 'volunteers',
    component: VolunteersComponent,
    canActivate: [hasRole],
    data: {
      requiredRoles: ['ADMIN', 'EMPLOYEE'],
    },
  },
  {
    path: 'volunteers/save',
    component: VolunteerSaveComponent,
    canActivate: [hasRole],
    data: {
      requiredRoles: ['ADMIN', 'EMPLOYEE'],
    },
  },
  {
    path: 'customers',
    component: CustomersComponent,
    canActivate: [hasRole],
    data: {
      requiredRoles: ['ADMIN', 'EMPLOYEE'],
    },
  },
  {
    path: 'customers/save',
    component: CustomerSaveComponent,
    canActivate: [hasRole],
    data: {
      requiredRoles: ['ADMIN', 'EMPLOYEE'],
    },
  },
  {
    path: 'donations',
    component: DonationsComponent,
    canActivate: [hasRole],
    data: {
      requiredRoles: ['CUSTOMER', 'ADMIN', 'EMPLOYEE'],
    },
  },
  {
    path: 'donations/save',
    component: DonationSaveComponent,
    canActivate: [hasRole],
    data: {
      requiredRoles: ['CUSTOMER'],
    },
  },
  {
    path: 'donations/update/:id',
    component: DonationUpdateComponent,
    canActivate: [hasRole],
    data: {
      requiredRoles: ['ADMIN'],
    },
  },
  {
    path: 'adoptions',
    component: AdoptionsComponent,
    canActivate: [hasRole],
    data: {
      requiredRoles: ['ADMIN', 'EMPLOYEE'],
    },
  },
  {
    path: 'invalid-access',
    component: InvalidAccessComponent,
  },
  {
    path: 'not-found',
    component: NotFoundComponent,
  },
  {
    path: '**',
    redirectTo: 'animals',
  },
];

@NgModule({
  imports: [
    InvalidAccessComponent,
    NotFoundComponent,
    RouterModule.forChild(routes),
  ],
  exports: [RouterModule],
})
export class DashboardRoutingModule {}
