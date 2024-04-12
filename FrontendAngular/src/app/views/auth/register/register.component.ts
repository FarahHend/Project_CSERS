import { Component } from '@angular/core';
import { AuthService } from '../../../../core/service/AuthenticationService'; 
import { RegisterRequest } from '../../../../core/models/RegisterRequest'; 
import { AuthenticationResponse } from '../../../../core/models/AuthenticationResponse'; 

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent {
  registerRequest: RegisterRequest = {
    firstname: '',
    lastname: '',
    email: '',
    password: '',
    role : 'USER'
  };

  constructor(private authService: AuthService) { }

  onSubmit(): void {
    // Set the role to 'USER'
    this.registerRequest.role = 'USER';
  
    this.authService.register(this.registerRequest).subscribe(
      (response: AuthenticationResponse) => {
        // Registration successful
        console.log('Registration successful:', response);
        // Optionally, redirect to another page or show a success message
      },
      (error) => {
        // Registration failed
        console.error('Registration failed:', error);
        // Handle error: display error message to the user
      }
    );
  }
}