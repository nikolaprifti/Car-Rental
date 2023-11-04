import {Component, OnInit} from '@angular/core';
import {EmployeeService} from "../../services/employee.service";
import {Employee} from "../../models/employee";

@Component({
  selector: 'app-employees',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
   employees: Employee[] ;
   isManager: boolean = false;
   user: Employee;
   constructor(private employeeService: EmployeeService) {}
  
   ngOnInit(): void {
    this.getAll();
    this.getRole();
  }

  getAll(){
    return this.employeeService.getAll().subscribe(
      {
        next:(res:Employee[]):void=>{
          this.employees=res;
        },
        error:err => {
          console.log(err);
          alert("Something went wrong");
        }

      }

    );
  }

  getRole(){
    if(sessionStorage.getItem('role') == "ROLE_MANAGER")
      this.isManager = true;
  }
}
