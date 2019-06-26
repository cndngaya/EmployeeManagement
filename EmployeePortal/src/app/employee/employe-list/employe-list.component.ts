import { Component, OnInit, OnDestroy } from '@angular/core';
import { EmployeListServiceService } from './employe-list-service.service';
import { DataServiceService } from '../../data-service.service';
import { EmployeeForm } from '../';
@Component({
  selector: 'app-employe-list',
  templateUrl: './employe-list.component.html',
  styleUrls: ['./employe-list.component.scss']
})
export class EmployeListComponent implements OnInit, OnDestroy {
  public employeeList: EmployeeForm[] = [];
  constructor(
    private employeListServiceService: EmployeListServiceService,
    private ds: DataServiceService
    ) { }

  ngOnInit() {
    this.employeeList = [];
    this.employeeList = this.ds.getEmployee() ? this.ds.getEmployee() : [];
    if (!this.employeeList.length) {
      this.employeListServiceService.getEmployeeList().subscribe((res) => {
        this.employeeList = res;
      }, (err) => {
        console.log('Error in fetching data from server::');
      });
    }
  }

  ngOnDestroy() {
    this.employeeList = [];
  }

}
