import { Component } from '@angular/core';

@Component({
  selector: 'app-register',
  standalone: true,
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  email: string = '';
  password: string = '';
  repassword: string = '';

  validatePassword(event: Event) {
    event.preventDefault();
    if (this.password !== this.repassword) {
      alert('The passwords do not match!');
      return;
    }
    alert('Registration successful!');
  }

  navigateToLogin() {
    window.location.href = '/login';
  }
}
