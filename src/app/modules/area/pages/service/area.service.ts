import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AreaInterface } from '../Interface/interface-area';
import { encrypt } from 'src/app/utils/encrypt';

@Injectable({
  providedIn: 'root',
})
export class AreaService {
  private API_SERVER = 'http://localhost:8020/area';

  constructor(private httpClient: HttpClient,
              private encry: encrypt) {}


  public getAllArea():Observable<AreaInterface[]>{
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get<AreaInterface[]>(this.API_SERVER + "/all",{headers});
  }

  saveArea(area:any):Observable<AreaInterface []>{
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.post<AreaInterface []>(this.API_SERVER+"/save/",area, {headers})
  }

  getArea(id:string | null):Observable<any>{
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.get(this.API_SERVER+"/"+id,{headers});
  }

  updateArea(id:string,dataArea:any){
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.put(this.API_SERVER+"/"+id,dataArea, {headers});
  }

  deleteArea(id: string):Observable<AreaInterface[]>{
    const enToken: any=localStorage.getItem('token')
    const token =''+ this.encry.decryptData(enToken);
    // const token: string | null= ''+localStorage.getItem('token')
    const headers = new HttpHeaders().set("Authorization", token);
    return this.httpClient.delete<AreaInterface[]>(this.API_SERVER+"/deletearea/"+id, {headers});
  }

}
