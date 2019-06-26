import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EmployeeRegistrationComponent, EmployeListComponent } from './employee';
const routes: Routes = [
  {
    path: 'employe-list',
    component: EmployeListComponent
  }, {
    path: 'employee-registration',
    component: EmployeeRegistrationComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
