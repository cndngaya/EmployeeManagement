import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, catchError, } from 'rxjs/operators';

import { EmployeeForm } from '../';
@Injectable({
  providedIn: 'root'
})
export class EmployeListServiceService {

  private staticUrl = 'assets/employe-list.json';
  constructor(private http: HttpClient) { }

  getEmployeeList(): Observable<EmployeeForm[]> {
    return this.http.get<EmployeeForm[]>(this.staticUrl)
          .pipe(
            map((data) => data),
            catchError(this.errorHandler)
          );
  }

  errorHandler(error: HttpErrorResponse) {
    return throwError(error.message || 'Error in fetching data from server');
  }
}
