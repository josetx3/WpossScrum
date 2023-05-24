import { HttpClient } from '@angular/common/http';
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
    return this.httpClient.get(this.API_SERVER + "/all");
  }

  public saveCustomer(customer: any): Observable<any> {
    return this.httpClient.post(this.API_SERVER + "/save/", customer);
  }

  public getCustomerById(id: string | null): Observable<any> {
    return this.httpClient.get(this.API_SERVER + "/" + id);
  }

  public updateCustomer(id: string, dataCustomer: any) {
    return this.httpClient.put(this.API_SERVER + "/" + id, dataCustomer);
  }

}
