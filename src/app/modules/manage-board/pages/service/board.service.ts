import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IBoard } from '../interface/board';
import { Employee } from 'src/app/modules/employees/pages/interface/employee';
import { Team } from 'src/app/modules/teams/pages/interface/team';
import { Tasks } from 'src/app/modules/teams/pages/interface/tasks';
import { UserStory } from 'src/app/modules/userStory/pages/interface/userStory';

@Injectable({
  providedIn: 'root',
})
export class BoardService {
  private API_SERVER = 'http://localhost:8020/board';

  constructor(private httpClient: HttpClient) {}

  public getAllBoard(): Observable<any> {
    return this.httpClient.get(this.API_SERVER + '/allboards');
  }
  public saveBoard(board: any): Observable<any> {
    return this.httpClient.post(this.API_SERVER + '/saveboard', board);
  }



  getUserStoryTeam(teamId: string): Observable<any> {
    return this.httpClient.get(
      'http://localhost:8020/userstory/userstoryteam/' + teamId
    );
  }
  getTeamArea(areaId: string): Observable<any> {
    return this.httpClient.get('http://localhost:8020/team/area/' + areaId);
  }

  deleteBoard(id: string): Observable<IBoard[]> {
    return this.httpClient.delete<IBoard[]>(
      this.API_SERVER + '/deleteboard/' + id
    );
  }

  updateBoard(idBoard: String, dataBoard: any) {
    return this.httpClient.put(
      this.API_SERVER + '/updateboard/' + idBoard,
      dataBoard
    );
  }

  getAllBoardById(idBoard: string | null): Observable<any> {
    return this.httpClient.get(this.API_SERVER + '/boardid/' + idBoard);
  }

  getEmployees(): Observable<Employee> {
    return this.httpClient.get<Employee>('http://localhost:8020/employee/all');
  }

  getAllTeam(): Observable<Team> {
    return this.httpClient.get<Team>('http://localhost:8020/team/all');
  }
  getAllTaskTeam(): Observable<Tasks> {
    return this.httpClient.get<Tasks>('http://localhost:8020/taskteam/all');
  }
  getAllUserStory(): Observable<UserStory> {
    return this.httpClient.get<UserStory>(
      'http://localhost:8020/userstory/userstory/all'
    );
  }



}
