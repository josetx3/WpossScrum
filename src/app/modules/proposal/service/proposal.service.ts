import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { encrypt } from 'src/app/utils/encrypt';

@Injectable({
  providedIn: 'root'
})
export class ProposalService {

  private API_SERVER = 'http://localhost:8020/proposal';

  constructor(
    private httpClient: HttpClient,
    private encry: encrypt) { }

    public getProposalById(proposalId: string): Observable<any> {
      console.log(proposalId)
      const enToken: any=localStorage.getItem('token');
      const token =''+ this.encry.decryptData(enToken);
      const headers = new HttpHeaders().set("Authorization", token);
      return this.httpClient.get(this.API_SERVER + "/"+ proposalId, {headers});
    }

  public getAllProposal(): Observable<any> {
    const enToken: any=localStorage.getItem('token');
    const token =''+ this.encry.decryptData(enToken);
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER + "/all", {headers});
  }

  public saveProposal(dataProposal: any): Observable<any> {
    const enToken: any=localStorage.getItem('token');
    const token =''+ this.encry.decryptData(enToken);
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.post(this.API_SERVER + "/save", dataProposal,{headers});
  }

  public updateProposal(dataProposal: any): Observable<any> {
    const enToken: any=localStorage.getItem('token');
    const token =''+ this.encry.decryptData(enToken);
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.put(this.API_SERVER + "/", dataProposal,{headers});
  }
}
