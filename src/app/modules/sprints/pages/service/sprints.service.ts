import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SprintsService {
  private API_SERVER = 'http://localhost:8020/sprint';

  constructor(private httpClient: HttpClient) {}

  public getAllASprint(): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER + '/all', {headers});
  }

  public saveSprint(sprint: any): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.post(this.API_SERVER + '/savesprint', sprint, {headers});
  }
  
  getSprintById(sprintId: String | null): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER + '/sprint/' + sprintId, {headers});
  }

  // updateSprint(idSprint:string| null,userStorySprint:any){
  //   return this.httpClient.put(this.API_SERVER+"/"+idSprint,userStorySprint);
  // }

  getTeamArea(areaId: string): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get('http://localhost:8020/team/area/' + areaId, {headers});
  }

  saveCalculationSprintPoints(dataPointsSprint: any): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.post('http://localhost:8020/sprintEmployee/savesprintemployee',dataPointsSprint, {headers});
  }

  getAllCalculationPercentageEmployee(): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER + '/sprint/employee/all',{headers});
  }

  // getAllEmployeesNoExistOnTeamBySprintId(
  //   sprintId: string | null
  // ): Observable<any> {
  //   return this.httpClient.get(
  //     'http://localhost:8020/employee/employeenoexistonsprint/' + sprintId
  //   );
  // }

  getAllEmployeesExistOnTeamBySprintId(teamId: string | null ): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get('http://localhost:8020/sprintEmployee/employeeteam/'+ teamId, {headers} );
  }


  getSprintEmployeeByTeamAndSprint(sprintId: string | null, teamId: string | null): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get('http://localhost:8020/sprintEmployee/employeeteam/' + sprintId +"/"+ teamId, {headers});
  }

  getAllEmployeeSprint(sprintEmployeeId: string | null, sprintId: string | null): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get('http://localhost:8020/sprintEmployee/sprintemployee/' + sprintEmployeeId +'/'+ sprintId, {headers});
  }

  updateEmployeeSprint(sprintEmployeeId: string | null, sprintId: any, dataEmployeSprint: any): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    console.log(dataEmployeSprint)
    return this.httpClient.put('http://localhost:8020/sprintEmployee/updatesprintemployee/' + sprintEmployeeId +'/'+sprintId, dataEmployeSprint, {headers});
  }
  scoreUserStory(sprintId: string | null, dataUserStory: any): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.put(this.API_SERVER + '/' + sprintId, dataUserStory, {headers});
  }
  getAllDatesSprint(sprintId: string | null): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
     return this.httpClient.get('http://localhost:8020/sprintday/sprint/' + sprintId, {headers});
  }

  public updateScoreSprint(sprintId: string | null, dataScoreSpring: any): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.put('http://localhost:8020/scoringSpring/update_score/' + sprintId, dataScoreSpring, {headers});
  }

  public getSprintDateById(sprintId: String | null): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER + '/sprintData/'+sprintId , {headers});
  }

  public getUseStoryRef(teamId: String | null, areaId: String | null): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get('http://localhost:8020/userstory/userstoryteam/'+teamId+'/'+areaId , {headers});
  }

  addUserStoryToSprint(dataSprintUserStory: any): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.post('http://localhost:8020/sprintuserstory/savesprintuserstory',dataSprintUserStory, {headers});
  }

  public getUseStoryDes(): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get('http://localhost:8020/userstory/userstoryteam/', {headers});
  }
}