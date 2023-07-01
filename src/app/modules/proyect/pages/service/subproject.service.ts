import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {SubprojectById} from '../interface/subprojects';

@Injectable({
  providedIn: 'root'
})
export class SubprojectService {

  private API_SERVER='http://localhost:8020/subproject';

  constructor(private httpClient : HttpClient) { }

  public getAllSubprojects():Observable<any>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER+"/all",{headers} );
  }


  public saveSubProject(subProyect:any):Observable<any>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.post(this.API_SERVER+"/save",subProyect, {headers});
  }

  getSubProjectById(idSubProyect:string | null):Observable<SubprojectById>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get<SubprojectById>(this.API_SERVER+"/"+idSubProyect, {headers});
  }
  updateSubProject(idSubProyect:string,dataSubProyect:any){
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.put(this.API_SERVER+"/"+idSubProyect,dataSubProyect, {headers});
  }

  getProyecto():Observable<any>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get('http://localhost:8020/project/all', {headers});
  }

  getTeam(): Observable<any>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get('http://localhost:8020/team/all', {headers});
  }

  getSubprojectToProyect(projectId: string):Observable<any>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER+'/project/'+projectId, {headers});
  }

}
