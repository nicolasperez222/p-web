import { Component, inject, signal, OnInit } from '@angular/core';
import { LoginDto } from '../../dto/login-dto';
import { AuthService } from '../../shared/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent implements OnInit {
  loginDto = signal(new LoginDto('', ''));

  private auth = inject(AuthService);
  private router = inject(Router)

  ngOnInit(): void {
    this.auth.logout();
  }

  login() {
    this.auth.login(this.loginDto()).subscribe({
      next: (jwt) => {
        console.log(jwt);
        this.router.navigate(['home']);
      },
      error: (err) => {
        console.error('Login failed:', err);
      },
    });
  }
}
