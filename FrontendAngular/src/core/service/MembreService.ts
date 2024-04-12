import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../service/AuthenticationService';
import { Membre } from '../models/Membre';
import { tap, catchError } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class MembreService {
  private apiUrl = 'http://localhost:8089/csers/membres';

  constructor(private http: HttpClient, private authService: AuthService) {}

  getAllMembres(): Observable<Membre[]> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.authService.getAccessToken()}`);
    return this.http.get<Membre[]>(`${this.apiUrl}/getall` , { headers })
    .pipe(
        tap((membres) => console.log('Retrieved all equipes:', membres)),
        catchError((error) => {
          console.error('Error retrieving all equipes:', error);
          throw error;
        })
      );
  }

  getMembreById(id: number): Observable<Membre> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.authService.getAccessToken()}`);
    return this.http.get<Membre>(`${this.apiUrl}/${id}` , { headers });
  }

  getMembersByEquipeId(equipeId: number): Observable<Membre[]> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.authService.getAccessToken()}`);
    return this.http.get<Membre[]>(`${this.apiUrl}/byEquipeId/${equipeId}`, { headers });
  }

  
  createMembreWithImage(membre: Membre): Observable<Membre> {
    const formData = new FormData();
    formData.append('nom', membre.nom);
    formData.append('prenom', membre.prenom);
    formData.append('email', membre.email);
    formData.append('poste', membre.poste);
    formData.append('number', membre.number);
    formData.append('competencesTechniques', membre.competencesTechniques);
    formData.append('certifications', membre.certifications);
    formData.append('experience', membre.experience ? membre.experience.toString() : '');
    formData.append('equipeInterventionId', membre.equipeInterventionId.toString());
    formData.append('imageFile', membre.imageFile);
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.authService.getAccessToken()}`);
    return this.http.post<Membre>(`${this.apiUrl}/createmembre`, formData, { headers });
  }

  updateMembre(id: number, membre: Membre): Observable<Membre> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.authService.getAccessToken()}`);
    return this.http.put<Membre>(`${this.apiUrl}/update/${id}`, membre , { headers });
  }

  deleteMembre(id: number): Observable<string> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.authService.getAccessToken()}`);
    return this.http.delete<string>(`${this.apiUrl}/delete/${id}` , { headers });
  }
}
