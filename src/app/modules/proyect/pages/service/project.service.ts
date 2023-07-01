import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private API_SERVER='http://localhost:8020/project'

  constructor(
    private httpClient:HttpClient
  ) { }

  public getAllProyect():Observable<any>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER+"/all", {headers});
  }
  public saveProyect(proyect:any):Observable<any>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.post(this.API_SERVER+"/save",proyect, {headers});
  }
  getProyectById(idProyect:string | null):Observable<any>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER+"/"+idProyect, {headers});
  }
  updateProyect(idProyect:string,dataProyect:any){
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.put(this.API_SERVER+"/"+idProyect,dataProyect, {headers});
  }

  getClient():Observable<any>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get('http://localhost:8020/client/all', {headers});
  }

  getArea(): Observable<any>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get('http://localhost:8020/area/all', {headers});
  }
}
