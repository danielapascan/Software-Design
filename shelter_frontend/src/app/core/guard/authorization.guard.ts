import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, Router } from '@angular/router';
import { PersonModel } from '../../shared/models/person.model';


export const hasRole: CanActivateFn = (route: ActivatedRouteSnapshot) => {
  const router: Router = inject(Router);
  const requiredRoles: string[] = route.data['requiredRoles'];
  const loggedPerson: PersonModel = getPerson();

  return requiredRoles.includes(loggedPerson.role)
    ? true
    : router.navigateByUrl('/dashboard/invalid-access');
};

const getPerson = (): PersonModel => {
  return JSON.parse(localStorage.getItem('loggedPerson') || '');
};
