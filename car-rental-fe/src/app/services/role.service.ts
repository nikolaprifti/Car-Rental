import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private BASEURL:string='http://localhost:8080';
  constructor(private http:HttpClient) { }

  getAllRoles(){
    return this.http.get(this.BASEURL+'/roles/all');
  }
}
