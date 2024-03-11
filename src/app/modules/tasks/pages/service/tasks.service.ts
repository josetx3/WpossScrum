import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { encrypt } from 'src/app/utils/encrypt';

@Injectable({
  providedIn: 'root'
})
export class TasksService {

  constructor(private httpClient: HttpClient,
              private encry: encrypt) { }

  public getStoryUserbyTeam(teamId: String |null,sprintId: String |null ): Observable<any>{
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get('http://localhost:8020/userstory/allByTeam/'+ teamId +'/' +sprintId, {headers});
  }

  public getTasksByUserStory(teamId: String |null, userStoryId: String |null): Observable<any>{
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get('http://localhost:8020/taskteam/taskteamhu/'+ teamId +'/'+ userStoryId , {headers});
  }

  public editTimeTasksByUseStory(tasksTeamId: String, dataEditHours: any): Observable<any>{
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.put('http://localhost:8020/taskteam/updatetask/'+ tasksTeamId ,dataEditHours, {headers});
  }

  public addTaskToUserStory( dataTask: any): Observable<any>{
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.post('http://localhost:8020/taskteam/createtask',dataTask, {headers});
  }

  public finishedTask(taskTeamId: String |null, idBoard: string, data:any): Observable<any>{
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.put('http://localhost:8020/taskteam/updatetaskstate/'+ taskTeamId+ '/' +idBoard ,data, {headers});
  }
}
