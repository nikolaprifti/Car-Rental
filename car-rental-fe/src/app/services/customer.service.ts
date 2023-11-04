import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {


  private  BASICURL:string='http://localhost:8080/customer';

  constructor( private http : HttpClient) { }

  getAll(){
    return this.http.get(this.BASICURL+'/all');
  }

  public login(NID:string){
    let body= {
      'NID':NID
    }
    return this.http.post(this.BASICURL + '/login', body);
  }
}




