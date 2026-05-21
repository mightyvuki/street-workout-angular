import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { KorisnikDTO } from '../models/KorisnikDTO';
import { TakmicenjeDTO } from '../models/TakmicenjeDTO';
import { RezultatDTO } from '../models/RezultatDTO';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8080/LSW/api/user';

  constructor(private http: HttpClient) { }

  getProfile(id: number): Observable<KorisnikDTO> {
    return this.http.get<KorisnikDTO>(`${this.apiUrl}/profile/${id}`);
  }

  updateProfile(id: number, data: Partial<KorisnikDTO>): Observable<KorisnikDTO> {
    return this.http.put<KorisnikDTO>(`${this.apiUrl}/profile/${id}`, data);
  }

  getResults(id: number): Observable<{ korisnik: KorisnikDTO; rezultati: RezultatDTO[]; discipline: string[] }> {
    return this.http.get<{ korisnik: KorisnikDTO; rezultati: RezultatDTO[]; discipline: string[] }>(
      `${this.apiUrl}/results/${id}`
    );
  }

  getUpcomingCompetitions(id: number): Observable<{ korisnik: KorisnikDTO; sledeca: TakmicenjeDTO[] }> {
    return this.http.get<{ korisnik: KorisnikDTO; sledeca: TakmicenjeDTO[] }>(
      `${this.apiUrl}/upcoming/${id}`
    );
  }
}
