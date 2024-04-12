import { Component, OnInit } from "@angular/core";
import { AuthService } from '../../../../core/service/AuthenticationService'; 
import { AuthenticationRequest } from "../../../../core/models/AuthenticationRequest";
import { Router } from "@angular/router";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
})
export class LoginComponent implements OnInit {
  email: string = "";
  password: string = "";
  loginError: string = "";

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {}

  login(): void {
    console.log('Attempting to login...');
    const authRequest: AuthenticationRequest = {
      email: this.email,
      password: this.password,
    };
  
    console.log('Auth request:', authRequest);
  
    this.authService.authenticate(authRequest).subscribe(
      (response) => {
        console.log('Authentication successful:', response);
        this.router.navigate(['/admin/settings']); 
      },
      (error) => {
        console.error('Authentication failed:', error);
        // Handle authentication failure (e.g., display error message)
      }
    );
  }}
  