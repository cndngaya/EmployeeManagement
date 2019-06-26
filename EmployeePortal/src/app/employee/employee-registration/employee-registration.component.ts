import { Component, OnInit } from '@angular/core';
import { EmployeeForm } from '../';
import { DataServiceService } from '../../data-service.service';
@Component({
  selector: 'app-employee-registration',
  templateUrl: './employee-registration.component.html',
  styleUrls: ['./employee-registration.component.scss']
})
export class EmployeeRegistrationComponent implements OnInit {
  employeeForm: EmployeeForm;
  constructor(private ds: DataServiceService) {
    this.employeeForm = {} as EmployeeForm;
    this.employeeForm.dob = new Date();
   }

  ngOnInit() {
  }

  // new user form data
  saveNewUserFormData(event: Event) {
    // event.preventDefault();
    console.log(this.employeeForm);
    this.employeeForm.dob = new Date(this.employeeForm.dob);
    this.ds.updateEmployee(this.employeeForm);
    this.employeeForm = {} as EmployeeForm;
    return false;
    // fs.exists('assets/employe-list.json', (exists) => {
    //   if (exists) {
    //     console.log('yes file exists');
    //   } else {
    //     const json = JSON.stringify(this.employeeForm);
    //     fs.writeFile('myjsonfile.json', json);
    //   }
    // });
  }

}
