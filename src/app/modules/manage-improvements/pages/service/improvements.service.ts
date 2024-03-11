import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ManageImprovements } from '../interface/improvements';
import { encrypt } from 'src/app/utils/encrypt';

@Injectable({
  providedIn: 'root'
})
export class ImprovementsService {
  private API_SERVER = 'http://localhost:8020/improvements';

  constructor(
    private httpClient: HttpClient,
    private encry: encrypt
  ) {
  }

  public getAllImprovements(): Observable<any> {
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER + "/all",  {headers});
  }

  public saveImprovements(improvements: any): Observable<any> {
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.post(this.API_SERVER + '/saveimprovements', improvements, {headers})
  }

  deleteImprovements(id: string):Observable<ManageImprovements[]>{
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.delete<ManageImprovements[]>(this.API_SERVER +'/deleteimprovements/'+id, {headers})
  }

  getImprovementsById(improvementsId: string):Observable<ManageImprovements[]>{
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get<ManageImprovements[]>(this.API_SERVER+'/improvementsId/'+improvementsId, {headers});
  }

  getTeamArea(areaId: string): Observable<any>{
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get('http://localhost:8020/team/area/'+areaId, {headers});
  }

  GetAllTask():Observable<any>{
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get('http://localhost:8020/taskteam/all', {headers})
  }
  GetAllObservations():Observable<any>{
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get('http://localhost:8020/observation/all', {headers})
  }

  public addObservationType(obsTypeDta: any): Observable<any> {
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.post('http://localhost:8020/observation/saveobservation', obsTypeDta, {headers})
  }

  public editObservationType(observationId: String,obsTypeDta: any): Observable<any> {
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.put('http://localhost:8020/observation/' +observationId, obsTypeDta, {headers})
  }

}
