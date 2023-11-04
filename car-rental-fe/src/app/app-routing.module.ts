import {Component, NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {EmployeeComponent} from "./components/employee/employee.component";
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { MainComponent } from './components/main/main.component';
import {CustomersComponent} from "./components/customers/customers.component";

const routes: Routes = [
  {path:'',component:MainComponent},
  {path:'login',component:LoginComponent},
  {path:'customer',component:CustomersComponent},
  {path:'register',component:RegisterComponent},
  {path:'employee',component:EmployeeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes), HttpClientModule, BrowserModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
