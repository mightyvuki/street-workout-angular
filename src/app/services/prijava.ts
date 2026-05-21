import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class PrijavaService {
  private apiUrl = 'http://localhost:8080/LSW/api/prijava';

  constructor(private http: HttpClient) {}

  prijaviSe(takmicenjeId: number, korisnikId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/prijaviSe/${takmicenjeId}`, { korisnikId });
  }

  odjaviSe(takmicenjeId: number, korisnikId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/odjaviSe/${takmicenjeId}`, { korisnikId });
  }
}
