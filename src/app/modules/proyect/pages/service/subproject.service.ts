import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SubprojectById} from '../interface/subprojects';

@Injectable({
  providedIn: 'root'
})
export class SubprojectService {

  private API_SERVER='http://localhost:8020/subproject';

  constructor(private httpClient : HttpClient) { }

  public getAllSubprojects():Observable<any>{
    return this.httpClient.get(this.API_SERVER+"/all");
  }


  public saveSubProject(subProyect:any):Observable<any>{
    return this.httpClient.post(this.API_SERVER+"/save",subProyect);
  }

  getSubProjectById(idSubProyect:string | null):Observable<SubprojectById>{
    return this.httpClient.get<SubprojectById>(this.API_SERVER+"/"+idSubProyect);
  }
  updateSubProject(idSubProyect:string,dataSubProyect:any){
    return this.httpClient.put(this.API_SERVER+"/"+idSubProyect,dataSubProyect);
  }

  getProyecto():Observable<any>{
    return this.httpClient.get('http://localhost:8020/project/all');
  }

  getTeam(): Observable<any>{
    return this.httpClient.get('http://localhost:8020/team/all');
  }
}
