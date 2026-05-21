import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TakmicenjeDTO } from '../models/TakmicenjeDTO';

@Injectable({ providedIn: 'root' })
export class TakmicenjeService {
  private baseUrl = 'http://localhost:8080/LSW/api/takmicenje';

  constructor(private http: HttpClient) { }

  getOdrzana(): Observable<any[]> {
    return this.http.get<TakmicenjeDTO[]>(`${this.baseUrl}/getOdrzana`);
  }

  getNajavljena(): Observable<any[]> {
    return this.http.get<TakmicenjeDTO[]>(`${this.baseUrl}/getNajavljena`);
  }

  getNajavljeno(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/najavljeno/${id}`);
  }

  getOdrzano(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/odrzano/${id}`);
  }

}
