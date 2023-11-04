import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {EmployeeService} from "../../services/employee.service";
import { Employee } from '../../models/employee';
import { EmployeeComponent } from '../employee/employee.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  employeeComponent: EmployeeComponent;
  username:string;
  password:string;
  constructor(private router:Router,
              private employeeService:EmployeeService) {
  }
  ngOnInit(): void {
  }

  onLogin() {
    this.employeeService.login(this.username, this.password).subscribe({
      next: (response: any) => {
        this.createSession(response.authorities[0].authority);
        this.router.navigate(['/employee']);
        // if (response.authorities[0].authority === 'ROLE_MANAGER') {
        //   this.employeeComponent.isManager = true;
        // } 
        // else {
        //   this.employeeComponent.isManager = false;
        // }
      },
      error: err => {
        if (err.status === 403)
          alert(err.error);
        else
          alert("Username or password invalid");
      }
    });
  }

  createSession(role){
    sessionStorage.setItem('auth', 'Basic ' + window.btoa(this.username + ':' + this.password));
    sessionStorage.setItem('role', role);
  }
}
