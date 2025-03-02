import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule], // FormsModule IMPORTÁLÁSA!
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private router: Router) {}

  login() {
    if (this.email.trim() === '' || this.password.trim() === '') {
      this.errorMessage = 'Töltse ki az összes mezőt!';
    } else {
      this.errorMessage = '';
      console.log("Sikeres bejelentkezés, átirányítás a /home oldalra");
      this.router.navigate(['/home']);
    }
  }

  navigateToRegister() {
    this.router.navigate(['/register']);
  }

  navigateToForgotPassword() {
    this.router.navigate(['/forgot-password'])
  }
}
