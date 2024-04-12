import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Equipe } from '../models/Equipe'; 
import { AuthService } from '../service/AuthenticationService';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class EquipeService {
  private apiUrl = 'http://localhost:8089/csers/equipes'; 

  constructor(private http: HttpClient, private authService: AuthService) {}

  createEquipe(equipeData: Equipe): Observable<Equipe> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.authService.getAccessToken()}`);
    
    return this.http.post<Equipe>(`${this.apiUrl}/create`, equipeData, { headers })
      .pipe(
        tap((createdEquipe) => console.log('Equipe created successfully:', createdEquipe)),
        catchError((error) => {
          console.error('Error creating Equipe:', error);
          throw error;
        })
      );
  }

  getAllEquipes(): Observable<Equipe[]> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.authService.getAccessToken()}`);
    return this.http.get<Equipe[]>(`${this.apiUrl}/getall` , { headers } )
      .pipe(
        tap((equipes) => console.log('Retrieved all equipes:', equipes)),
        catchError((error) => {
          console.error('Error retrieving all equipes:', error);
          throw error;
        })
      );
  }

  getAllEquipeNames(): Observable<string[]> {
    console.log("Fetching all equipe names...");
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.authService.getAccessToken()}`);
    return this.http.get<Equipe[]>(`${this.apiUrl}/getall`, { headers }).pipe(
      tap(equipes => console.log("Retrieved all equipes:", equipes)),
      map(equipes => equipes.map(equipe => equipe.nomEquipe)),
      tap(names => console.log("Extracted equipe names:", names)),
      catchError((error) => {
        console.error('Error retrieving all equipes:', error);
        throw error;
      })
    );
  }
  

  getEquipeById(id: number): Observable<Equipe> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.authService.getAccessToken()}`);
    return this.http.get<Equipe>(`${this.apiUrl}/${id}` , { headers })
      .pipe(
        tap((equipe) => console.log('Retrieved equipe:', equipe)),
        catchError((error) => {
          console.error('Error retrieving equipe:', error);
          throw error;
        })
      );
  }

  getMemberImagesByEquipeId(equipeId: number): Observable<any> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.authService.getAccessToken()}`);
    return this.http.get<any>(`${this.apiUrl}/${equipeId}/membres/images`, { headers } )
      .pipe(
        tap((images) => console.log('Retrieved member images:', images)),
        catchError((error) => {
          console.error('Error retrieving member images:', error);
          throw error;
        })
      );
  }

 
  updateEquipe(id: number, equipeData: Equipe): Observable<Equipe> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.authService.getAccessToken()}`);
    return this.http.put<Equipe>(`${this.apiUrl}/update/${id}`, equipeData, { headers })
      .pipe(
        tap((updatedEquipe) => console.log('Equipe updated successfully:', updatedEquipe)),
        catchError((error) => {
          console.error('Error updating equipe:', error);
          throw error;
        })
      );
  }

  deleteEquipe(id: number): Observable<void> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.authService.getAccessToken()}`);
    return this.http.delete<void>(`${this.apiUrl}/${id}`, { headers })
      .pipe(
        tap(() => console.log(`Equipe with ID ${id} deleted successfully`)),
        catchError((error) => {
          console.error('Error deleting equipe:', error);
          throw error;
        })
      );
  }
}
