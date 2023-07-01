import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root',
})
export class TeamsService {
  private API_SERVER = 'http://localhost:8020/team';

  constructor(private httpClient: HttpClient) {}

  public getAllTeams(): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER + '/all', {headers});
  }

  public saveTeam(manage_teams: any): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.post(this.API_SERVER + '/save', manage_teams, {headers});
  }

  getTeamById(id: string | null): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER + '/' + id, {headers});
  }

  updateTeam(id: string, team: any) {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.put(this.API_SERVER + '/' + id, team, {headers});
  }

  addEmployeeTeam(teamId: string | null, teamEmployee: string[]): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.put(this.API_SERVER + '/saveemployetoteam/' + teamId, teamEmployee, {headers});
  }

  getTeamArea(areaId: string): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER + '/area/' + areaId, {headers});
  }

  getEmployeesInTeam(teamId: string): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER + '/team/' + teamId + '/employees', {headers});
  }






}
