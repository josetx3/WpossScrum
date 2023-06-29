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
  public isAuthenticated():boolean{
    const token = localStorage.getItem('token');
    if(token==='' || token===null || token===undefined){
      return false;
    }else{
      return true;
    }
  }

  public register(employee: any): Observable<any> {
    return this.httpClient.post('http://localhost:8020/employee' + '/save/', employee);
  }


}
