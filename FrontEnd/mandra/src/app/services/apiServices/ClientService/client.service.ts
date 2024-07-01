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


  getDisabledClients(page: number, size: number): Observable<any> {
    const url = `${this.baseUrl}/clients/disabled?page=${page}&size=${size}`;
    return this.http.get<any>(url, this.httpOptions)
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }


  getActiveClients(page: number, size: number): Observable<any> {
    const url = `${this.baseUrl}/clients/active?page=${page}&size=${size}`;
    return this.http.get<any>(url, this.httpOptions)
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

  changeClientStatus(clientId: number): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/clients/${clientId}/change-account-state`, {  withCredentials: true})
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }


  deleteClient(clientId: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/clients/${clientId}/delete`, {  withCredentials: true})
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

  updateClientImage(clientId: number, formData: FormData): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/clients/${clientId}/edit-image`, formData, {  withCredentials: true})
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

  updateClient(clientId: number, body: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/clients/${clientId}/edit`, body,this.httpOptions)
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

}
