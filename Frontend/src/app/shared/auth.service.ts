import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { LoginDto } from '../dto/login-dto';
import { map, Observable } from 'rxjs';
import { JwtAuthenticationResponse } from '../dto/jwt-authentication-response';
import { environment } from '../../environments/environment';

const JWT_TOKEN = 'jwt-token';
const EMAIL = 'user-email';
const ROLE = 'user-role';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  http = inject(HttpClient);

  isLoggedIn = signal(false);

  private isBrowser(): boolean {
    return typeof window !== 'undefined';
  }

  constructor() {
    if (this.isBrowser() && sessionStorage.getItem(JWT_TOKEN)) {
      this.isLoggedIn.set(true);
    }
  }

  login(loginDto: LoginDto): Observable<JwtAuthenticationResponse> {
    return this.http
      .post<JwtAuthenticationResponse>(
        `${environment.serverUrl}/auth/login`,
        loginDto
      )
      .pipe(
        map((jwt) => {
          if (this.isBrowser()) {
            sessionStorage.setItem(JWT_TOKEN, jwt.token);
            sessionStorage.setItem(EMAIL, jwt.email);
            sessionStorage.setItem(ROLE, jwt.role);
          }

          this.isLoggedIn.set(true);

          return jwt;
        })
      );
  }

  logout() {
    if (this.isBrowser()) {
      sessionStorage.removeItem(JWT_TOKEN);
      sessionStorage.removeItem(EMAIL);
      sessionStorage.removeItem(ROLE);
    }

    this.isLoggedIn.set(false);
  }

  isAuthenticated() {
    return this.isLoggedIn();
  }

  token() {
    return this.isBrowser() ? sessionStorage.getItem(JWT_TOKEN) : null;
  }

  role() {
    return this.isBrowser() ? sessionStorage.getItem(ROLE) : null;
  }
}
