import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Employee} from "../models/employee";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private HTTPURL = 'http://localhost:8080/employee';

  constructor(private http:HttpClient) { }

  public login(username:string, password:string){
    let body= {
      'username': username,
      'password': password
    }
    return this.http.post(this.HTTPURL + '/login', body);
  }

  public getAll(){
    return this.http.get(this.HTTPURL + '/all');
  }
  
  public create(employee:Employee){
    return this.http.post(this.HTTPURL + '/create', employee);
  }
}
