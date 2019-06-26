import { Injectable } from '@angular/core';
import { EmployeeForm } from './employee';
@Injectable({
  providedIn: 'root'
})
export class DataServiceService {
  private userList: EmployeeForm[] = [];
  constructor() { }

  updateEmployee(data: EmployeeForm) {
    if (data) {
      this.userList.push(data);
    }
  }
  getEmployee(): EmployeeForm[] {
    return this.userList;
  }
}
