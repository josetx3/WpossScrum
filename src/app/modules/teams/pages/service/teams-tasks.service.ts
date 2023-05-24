import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class TeamsTasksService {

  private API_SERVER ='http://localhost:8020/taskteam';

  constructor(private httpClient: HttpClient) {
  }
  public getAllTeamTasks():Observable<any>{
    return this.httpClient.get(this.API_SERVER+"/all");
  }
  public saveTeamTasks(task:any):Observable<any>{
    return this.httpClient.post(this.API_SERVER+"/createtask",task);
  }
  getTeamTasks(id:string| null):Observable<any>{
    return this.httpClient.get(this.API_SERVER+"/"+id);
  }
  updateTeamTasks(id:string,dataTeam:any){
    return this.httpClient.put(this.API_SERVER+"/updatetask/"+id,dataTeam)
  }
  deleteTeamTasks(id:string):Observable<any>{
    console.log(id)
    return this.httpClient.delete(this.API_SERVER+"/deleteTaskTeam/"+id )

  }
  getAllTaskTeamByTeamId(TeamId:string| null):Observable<any>{
    return this.httpClient.get(this.API_SERVER+"/taskteam/"+TeamId)
  }

}
