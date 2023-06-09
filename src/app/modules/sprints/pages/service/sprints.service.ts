import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SprintsService {
  private API_SERVER = 'http://localhost:8020/sprint';

  constructor(private httpClient: HttpClient) {}

  public getAllASprint(): Observable<any> {
    return this.httpClient.get(this.API_SERVER + '/all');
  }
  public saveSprint(sprint: any): Observable<any> {
    return this.httpClient.post(this.API_SERVER + '/savesprint', sprint);
  }
  getSprintById(sprintId: string | null): Observable<any> {
    return this.httpClient.get(this.API_SERVER + '/sprint/' + sprintId);
  }

  // updateSprint(idSprint:string| null,userStorySprint:any){
  //   return this.httpClient.put(this.API_SERVER+"/"+idSprint,userStorySprint);
  // }

  getTeamArea(areaId: string): Observable<any> {
    return this.httpClient.get('http://localhost:8020/team/area/' + areaId);
  }
  saveCalculationSprintPoints(dataPointsSprint: any): Observable<any> {
    return this.httpClient.post('http://localhost:8020/sprintEmployee/savesprintemployee',
      dataPointsSprint
    );
  }
  getAllCalculationPercentageEmployee(): Observable<any> {
    return this.httpClient.get(this.API_SERVER + '/sprint/employee/all');
  }

  // getAllEmployeesNoExistOnTeamBySprintId(
  //   sprintId: string | null
  // ): Observable<any> {
  //   return this.httpClient.get(
  //     'http://localhost:8020/employee/employeenoexistonsprint/' + sprintId
  //   );
  // }
  getAllEmployeesExistOnTeamBySprintId(
    sprintId: string | null
  ): Observable<any> {
    return this.httpClient.get(
      'http://localhost:8020/sprintEmployee/allsprintemployee/'
    );
  }

  saveScoreSprintFinal(
    sprintId: string | null,
    sprintScoreFinal: any
  ): Observable<any> {
    return this.httpClient.put(
      'http://localhost:8020/sprintday/update-score/' + sprintId,
      sprintScoreFinal
    );
  }
  getAllEmployeeSprint(sprintEmployeeId: string | null): Observable<any> {
    return this.httpClient.get(
      this.API_SERVER + '/sprint-employee/id/' + sprintEmployeeId
    );
  }
  updateEmployeeSprint(
    sprintEmployeeId: string | null,
    sprintEmployee: any
  ): Observable<any> {
    return this.httpClient.put(
      this.API_SERVER + '/sprint-employee/update/' + sprintEmployeeId,
      sprintEmployee
    );
  }
  scoreUserStory(sprintId: string | null, dataUserStory: any): Observable<any> {
    return this.httpClient.put(this.API_SERVER + '/' + sprintId, dataUserStory);
  }
  getAllDatesSprint(sprintId: string | null): Observable<any> {
    return this.httpClient.get(
      'http://localhost:8020/sprintday/sprint/' + sprintId
    );
  }
}



//employeeId 0ab1c469-6711-4217-85db-2915dbca4d2e
//sprintId: 291d8565-998f-4acf-8968-b50726316d33
//areaId: 20be06ff-614c-4279-b064-def63fbf5ef6
//teamId: c33faf49-9d2e-4997-8af7-189e9ca90fea
