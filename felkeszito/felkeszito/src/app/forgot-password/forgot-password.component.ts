import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [ FormsModule], 
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {
  email: string = ''; // Hiányzó változó pótolva

  constructor(private router: Router) {}

  resetPassword() {
    if (!this.email) {
      alert('Please enter a valid email address!');
      return;
    }

    
  }
}
