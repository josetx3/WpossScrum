import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { encrypt } from 'src/app/utils/encrypt';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private API_SERVER = 'http://localhost:8020/client';

  constructor(private httpClient: HttpClient,
              private encry: encrypt) {
  }

  public getAllCustomer(): Observable<any> {
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER + "/all",{headers});
  }

  public saveCustomer(customer: any): Observable<any> {
    const enToken: any=localStorage.getItem('token');
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.post(this.API_SERVER + "/save/", customer,{headers});
  }

  public getCustomerById(id: string | null): Observable<any> {
    const enToken: any=localStorage.getItem('token');
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER + "/" + id,{headers});
  }

  public updateCustomer(id: string, dataCustomer: any) {
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.put(this.API_SERVER + "/" + id, dataCustomer,{headers});
  }

}
