import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private router: Router) {
    // Ellenőrzés, hogy van-e már felhasználó (pl. helyi tárolásból)
    if (typeof window !== 'undefined') {  
      const user = localStorage.getItem('user');
      if (user) {
        this.router.navigate(['/login']); // Ha van felhasználó, irány a login
      }
    }
  }

  ngOnInit(): void {
    // Ez a rész most már nem kell, mert a constructorban megcsináltuk!
    // const user = localStorage.getItem('user');
    // if (user) {
    //   this.router.navigate(['/login']); 
    // }
  }

  registerUser() {
    localStorage.setItem('user', 'exampleUser');
    this.router.navigate(['/login']);
  }
}