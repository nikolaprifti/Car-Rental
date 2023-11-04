import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {EmployeeService} from "../../services/employee.service";
import {RoleService} from "../../services/role.service";
import {Router} from "@angular/router";
import {Employee} from "../../models/employee";
import {Role} from "../../models/role";


@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

    employee: Employee;
    form: FormGroup;
    show: boolean;
    passwordClass: string = "password";
    roles: Role[];
    disabled: boolean = false;

    constructor(private formBuilder: FormBuilder,
                private employeeService: EmployeeService,
                private roleService:RoleService,
                private router:Router) {
    }

    ngOnInit(): void {
        this.form = this.formBuilder.group({
            employeeId: [null],
            username: [null, Validators.required],
            password: [null, Validators.required],
            email:[null],
            name: [null],
            role: [null, Validators.required]
        });
        this.roleService.getAllRoles().subscribe({
            next: (roles:Role[])=> {
                this.roles = roles;
            },
            error: err => alert(err.error)
        })
    }

    register() {
        this.employee = this.form.value;
        console.log(this.employee);
        this.employeeService.create(this.employee).subscribe({
            next: ()=> {
                alert("User created");
                this.router.navigate(['/employees']);
            },
            error: err => alert(err.error.message)
        })
    }

    toggleShow() {
        this.show = !this.show
        this.passwordClass = this.show ? 'text' : 'password'
    }

    setDisabled() {
        console.log('here')
        this.disabled = true
    }
}
