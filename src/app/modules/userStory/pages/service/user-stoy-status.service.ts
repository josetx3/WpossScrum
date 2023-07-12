import { Injectable } from '@angular/core';
import {Observable, observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserStoyStatusService {

  private API_SERVER = 'http://localhost:8020/userstorystatus';


  constructor( private httpCliente: HttpClient) { }

  public getAlluser_story_status(): Observable<UserStoyStatusService>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpCliente.get<UserStoyStatusService>(this.API_SERVER+"/statusall", {headers})
  }

  public addEstateHU(dataEstateHU: any): Observable<UserStoyStatusService>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpCliente.post<UserStoyStatusService>(this.API_SERVER+ '/savestatus',dataEstateHU, {headers})
  }
}
