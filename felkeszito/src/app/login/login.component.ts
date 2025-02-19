import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private router: Router) { }

  loginUser() {
    // Bejelentkezési logika (példa)
    this.router.navigate(['/home']); // Sikeres bejelentkezés után átirányítás
  }
}