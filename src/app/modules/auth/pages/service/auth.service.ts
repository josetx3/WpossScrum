import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { encrypt } from 'src/app/utils/encrypt';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private API_SERVER = 'http://localhost:8020/auth';
  private tokenExpiration: number= 3600000;

  constructor(private httpClient: HttpClient,
              private encry: encrypt) { }

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
    return this.httpClient.post('http://localhost:8020/employee/save/', employee);
  }

  setTokenExpiration(expiration: number): void {
    this.tokenExpiration = expiration;
  }

  checkTokenExpiration(): boolean {
   
    const horaInicioSesion = localStorage.getItem('horaInicio');
    if (horaInicioSesion) {
      const horaInicio = new Date(horaInicioSesion);
      const horaActual = new Date();

      const diferenciaTiempo = horaActual.getTime() - horaInicio.getTime();

      if (diferenciaTiempo >= this.tokenExpiration) {
        return true;
      }else {
        return false;
      }
      } 
      return false;
}
}
