import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { ProcessHttpmsgService } from 'src/app/Shared/process-httpmsg-service';

@Injectable({
  providedIn: 'root'
})
export class OwnerService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };


  constructor(private http: HttpClient, @Inject('BaseURL') private baseUrl: string,
    private processHTTPMsgService: ProcessHttpmsgService) { }


  registerOwner(owner: any): Observable<any> {
    return this.http.post<any>(this.baseUrl + "/owners/register", owner, this.httpOptions)
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }


  getOwnerById(ownerId: number): Observable<any> {
    return this.http.get<any>(this.baseUrl + `/owners/${ownerId}`, this.httpOptions)
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }


}
