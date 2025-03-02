import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  email: string = '';
  password: string = '';
  repassword: string = '';

  constructor(private router: Router) {}

  validatePassword(event: Event) {
    event.preventDefault();
    if (this.password !== this.repassword) {
      alert('The passwords do not match!');
      return;
    }
    alert('Registration successful!');
  }

  navigateToLogin() {
    this.router.navigate(['/login'])
  }
}
