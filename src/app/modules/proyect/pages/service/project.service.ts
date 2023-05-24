import { HttpClient } from '@angular/common/http';
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
    return this.httpClient.get(this.API_SERVER+"/all");
  }
  public saveProyect(proyect:any):Observable<any>{
    return this.httpClient.post(this.API_SERVER+"/save",proyect);
  }
  getProyectById(idProyect:string | null):Observable<any>{
    return this.httpClient.get(this.API_SERVER+"/"+idProyect);
  }
  updateProyect(idProyect:string,dataProyect:any){
    return this.httpClient.put(this.API_SERVER+"/"+idProyect,dataProyect);
  }

  getClient():Observable<any>{
    return this.httpClient.get('http://localhost:8020/client/all');
  }

  getArea(): Observable<any>{
    return this.httpClient.get('http://localhost:8020/area/all');
  }
}
