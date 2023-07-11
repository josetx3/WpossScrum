import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TasksService {

  constructor(private httpClient: HttpClient) { }

  public getStoryUserbyTeam(teamId: String |null): Observable<any>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get('http://localhost:8020/userstory/allByTeam/'+ teamId, {headers});
  }

  public getTasksByUserStory(teamId: String |null, userStoryId: String |null): Observable<any>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get('http://localhost:8020/taskteam/taskteam/'+ teamId +'/'+ userStoryId , {headers});
  }

  public editTimeTasksByUseStory(tasksTeamId: String, dataEditHours: any): Observable<any>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    //arreglar la ruta 
    return this.httpClient.put('http://localhost:8020/taskteam/updatetaskhours/'+ tasksTeamId ,dataEditHours, {headers});
  }
}
