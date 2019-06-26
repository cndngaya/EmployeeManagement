import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EmployeeRegistrationComponent, EmployeListComponent } from './';
@NgModule({
  declarations: [
    EmployeeRegistrationComponent,
    EmployeListComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class EmployeeModule { }
