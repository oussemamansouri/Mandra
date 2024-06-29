import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { ProcessHttpmsgService } from 'src/app/Shared/process-httpmsg-service';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };


  constructor(private http: HttpClient, @Inject('BaseURL') private baseUrl: string,
    private processHTTPMsgService: ProcessHttpmsgService) { }


  registerClient(client: any): Observable<any> {
    return this.http.post<any>(this.baseUrl + "/clients/register", client, this.httpOptions)
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

  getClientById(clientId: number): Observable<any> {
    return this.http.get<any>(this.baseUrl + `/clients/${clientId}`, this.httpOptions)
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

}
