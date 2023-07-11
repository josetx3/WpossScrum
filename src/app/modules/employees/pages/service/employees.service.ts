import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EmployeesService {
  private API_SERVER = 'http://localhost:8020/employee';

  constructor(private httpClient: HttpClient) {}

  public getAllEmployee(): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
  return this.httpClient.get(this.API_SERVER + '/all', {headers});
  }

  public saveEmployee(employee: any): Observable<any> {
    return this.httpClient.post(this.API_SERVER + '/save/', employee);
  }

  getEmployeeById(id: string | null): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER + '/' + id, {headers});
  }

  updateEmployee(id: string, employee: any) {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.put(this.API_SERVER + '/' + id, employee, {headers});
  }

  updateEmployee2(id: string, actual:string, employee: any) {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.put(this.API_SERVER + '/' + id + '/' +actual, employee, {headers});
  }

  getEmployeesAddToTeam(teamId: string | null): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER + '/employeteam/' + teamId, {headers});
  }

  getEmployeeToTeam(teamId: string | null): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER + '/employeteam/' + teamId, {headers});
  }

  getAllnoExistAndTeam(teamId: string | null): Observable<any>{
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER + '/employeenoteam/' + teamId,{headers})
  }

  public deleteEmployee(employeeId: String){
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    //arreglar la ruta
    return this.httpClient.delete(this.API_SERVER + '/'+ employeeId,{headers})
  }
}
