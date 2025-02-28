import { Injectable } from '@angular/core';
import { EmployeeModel } from '../../../shared/models/employee.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpParams } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) { }

  getAll(sortBy: string = 'Salary', pageNumber:number): Observable<{ employees: EmployeeModel[], page: any }> {
    return this.http.get<any>('employees/all',{
      params: {
        sortBy,
        pageNumber
      }
      }).pipe(
        map(response => ({
          employees: response.content,
          page: response
        }))
      );
  }

  deleteEmployee(employeeId: string): Observable<any> {
    return this.http.delete<any>(`employees/all/${employeeId}`);
  }

  saveEmployee(employee: EmployeeModel): Observable<any> {
    return this.http.post<any>('employees/all', employee);
  }

  giveRaise(employeeId: string, employee: EmployeeModel): Observable<any> {
    return this.http.put<any>(`employees/all/${employeeId}`, employee);
  }

}
