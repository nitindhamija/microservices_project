import { Component, OnInit } from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import {FormBuilder,FormGroup,Validators} from '@angular/forms';
import { first } from 'rxjs/operators';
import { AuthenticationService } from '../_services/authentication.service';
import {User} from '../_model/user';
import { userimpl } from '../_model/UserImpl';
@Component({
  //selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls:['../../assets/css/bootstrap.min.css']
 
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
    submitted = false;
    loading = false;
    returnUrl: string;
    error = '';
    
    user:User;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService
    ) {
        this.user = new userimpl();
    }

    ngOnInit(){
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });

        // logout the person when he opens the app for the first time
        this.authenticationService.logout();

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    // convenience getter for easy access to form fields
    get f(){
        return this.loginForm.controls;
    }

    // on submit
    onSubmit(){
        this.submitted = true;

        // stop if form is invalid
        if(this.loginForm.invalid){
            return;
        }

        this.loading = true;
        this.user.userName=this.f.username.value;
        this.user.password=this.f.password.value;
        //this.authenticationService.login(this.f.username.value, this.f.password.value)
        this.authenticationService.login(this.user)
       // .pipe(first())
        .subscribe(
            data => {
                if(data.token){
                    //             // store user details and jwt token in the local storage to keep the user logged in between page refreshes
                                localStorage.setItem('token', data.token);
                             }
                //this.router.navigate([this.returnUrl]);
                this.router.navigate(['']);
                
            },
            error => {
                this.error = error;
                this.loading = false;
            }
        )
    }
}
