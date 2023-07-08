import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class TeamsTasksService {

  private API_SERVER ='http://localhost:8020/taskteam';

  constructor(private httpClient: HttpClient) {
  }

  public getAllTeamTasks():Observable<any>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER+"/all", {headers});
  }

  public saveTeamTasks(task:any):Observable<any>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.post(this.API_SERVER+"/createtask",task, {headers});
  }

  getTeamTasks(id:string| null):Observable<any>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER+"/"+id, {headers});
  }

  updateTeamTasks(id:string,dataTeam:any){
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.put(this.API_SERVER+"/updatetask/"+id,dataTeam,{headers})
  }

  deleteTeamTasks(id:string):Observable<any>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.delete(this.API_SERVER+"/deleteTaskTeam/"+id, {headers} )

  }
  getAllTaskTeamByTeamId(TeamId:string| null):Observable<any>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER+"/taskteam/"+TeamId, {headers})
  }

}
