import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AreaInterface } from '../Interface/interface-area';

@Injectable({
  providedIn: 'root',
})
export class AreaService {
  private API_SERVER = 'http://localhost:8020/area';

  constructor(private httpClient: HttpClient) {}


  public getAllArea():Observable<AreaInterface[]>{
    return this.httpClient.get<AreaInterface[]>(this.API_SERVER + "/all");
  }

  saveArea(area:any):Observable<AreaInterface []>{
    return this.httpClient.post<AreaInterface []>(this.API_SERVER+"/save/",area)
  }

  getArea(id:string | null):Observable<any>{
    return this.httpClient.get(this.API_SERVER+"/"+id);
  }

  updateArea(id:string,dataArea:any){
    return this.httpClient.put(this.API_SERVER+"/"+id,dataArea);
  }

  deleteArea(id: string):Observable<AreaInterface[]>{
    return this.httpClient.delete<AreaInterface[]>(this.API_SERVER+"/deletearea/"+id);
  }

}
