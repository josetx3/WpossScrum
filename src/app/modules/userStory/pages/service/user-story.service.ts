import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStory } from '../interface/userStory';

@Injectable({
  providedIn: 'root'
})
export class userStoryService {

  private API_SERVER  ="http://localhost:8020/subproject";

  constructor(private httpClient: HttpClient) { }

  public getAllSubprojects():Observable<any>{
    return this.httpClient.get(this.API_SERVER+"/all");
  }

  public getAllUser_story():Observable<any>{
    return this.httpClient.get("http://localhost:8020/userstory/userstory/all");
  }
  public saveUser_story(user_story:any):Observable<any>{
    return this.httpClient.post("http://localhost:8020/userstory/save",user_story);
  }
  getUserStoryById(id:string | null):Observable<any>{
    return this.httpClient.get(this.API_SERVER+"/"+id);
  }
  updateUserStory(id:string,dataUserStory:any){
    return this.httpClient.put(this.API_SERVER+"/"+id,dataUserStory);
  }
  public getAllUserStoryToSubproject(subProjectId: string | null):Observable<UserStory>{
    return this.httpClient.get<UserStory>(this.API_SERVER+"/subproject/"+subProjectId)
  }
  public getUserStoryToTeam(teamId: string){
    return this.httpClient.get(this.API_SERVER+'/userstoryteam/'+teamId)
  }


}
