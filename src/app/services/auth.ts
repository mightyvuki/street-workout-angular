import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { KorisnikDTO } from '../models/KorisnikDTO';

@Injectable({ providedIn: 'root' })
export class AuthService {

  user = signal<KorisnikDTO | null>(null); // signal za trenutno ulogovanog korisnika
  private apiUrl = 'http://localhost:8080/LSW/api/auth';

  constructor(private http: HttpClient) {}

  login(credentials: { username: string; password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, {
      username: credentials.username,
      password: credentials.password
    }).pipe(
      tap((response: any) => {
        localStorage.setItem('token', response.token);
        localStorage.setItem('user', JSON.stringify(response.user));
        this.user.set(response.user);
      })
    );
  }

  register(korisnik: KorisnikDTO): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, korisnik);
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.user.set(null);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  getUser(): KorisnikDTO | null {
    const storedUser = localStorage.getItem('user');
    return storedUser ? JSON.parse(storedUser) : null;
  }
}
