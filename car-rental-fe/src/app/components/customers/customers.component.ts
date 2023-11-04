import {Component, OnInit} from '@angular/core';
import {Customer} from "../../models/customer";
import {CustomerService} from "../../services/customer.service";
import {Employee} from "../../models/employee";

@Component({
  selector: 'app-costumers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})

export class CustomersComponent implements OnInit{
onLogin() {
throw new Error('Method not implemented.');
}
    customers:Customer[] ;
    NID: string;
    constructor(private customerService:CustomerService) {
    }
    ngOnInit(): void {
        this.findAll();
    }


     findAll() {
         return this.customerService.getAll().subscribe(
             {
                 next:(res:Customer[]):void=>{
                     this.customers=res;
                 },
                 error:err => {
                     console.log(err);
                     alert("Something went wrong");
                 }
             }
         );
     }

}
