import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private API_SERVER = 'http://localhost:8020/client';

  constructor(private httpClient: HttpClient) {
  }

  public getAllCustomer(): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER + "/all",{headers});
  }

  public saveCustomer(customer: any): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.post(this.API_SERVER + "/save/", customer,{headers});
  }

  public getCustomerById(id: string | null): Observable<any> {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER + "/" + id,{headers});
  }

  public updateCustomer(id: string, dataCustomer: any) {
    const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.put(this.API_SERVER + "/" + id, dataCustomer,{headers});
  }

}
