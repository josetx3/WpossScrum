import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private API_SERVER = 'http://localhost:8020/auth';

  constructor(private httpClient: HttpClient) { }

  public PostEmailAndClave(employee: any): Observable<any> {
    
    return this.httpClient.post(this.API_SERVER+'/login', employee);

  }

}
