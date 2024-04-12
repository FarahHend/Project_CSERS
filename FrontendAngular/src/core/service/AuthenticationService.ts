import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { AuthenticationResponse } from '../models/AuthenticationResponse';
import { RegisterRequest } from '../models/RegisterRequest';
import { AuthenticationRequest } from '../models/AuthenticationRequest';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8089/csers/api/v1/auth'; 
  private accessTokenKey = 'access_token';

  constructor(private http: HttpClient) { }

  private saveToken(token: string): void {
    console.log('Saving access token:', token);
    localStorage.setItem(this.accessTokenKey, token);
    console.log('Saved access token:', localStorage.getItem(this.accessTokenKey));
  }

  register(request: RegisterRequest): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${this.baseUrl}/register`, request)
      .pipe(
        tap(response => this.saveToken(response.access_token))
      );
  }

  authenticate(request: AuthenticationRequest): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${this.baseUrl}/authenticate`, request)
      .pipe(
        tap(response => {
          console.log('Response from backend:', response); 
          this.saveToken(response.access_token); 
        })
      );
  }

  refreshToken(): Observable<void> {
    console.log('Refreshing access token...');
    return this.http.post<void>(`${this.baseUrl}/refresh-token`, null);
  }

  getAccessToken(): string | null {
    const token = localStorage.getItem(this.accessTokenKey);
    console.log('Retrieved access token:', token);
    return token;
  }

  logout(): void {
    localStorage.removeItem(this.accessTokenKey);
  }
}
