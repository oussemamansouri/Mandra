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


  updateOwnerCin(ownerId: number, image:FormData): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/owners/${ownerId}/upload-cin-image`,image, {  withCredentials: true})
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

  updateOwnerProof(ownerId: number, pdf:FormData): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/owners/${ownerId}/upload-proof`,pdf, {  withCredentials: true})
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

  getDisabledOwners(page: number, size: number): Observable<any> {
    const url = `${this.baseUrl}/owners/disabled?page=${page}&size=${size}`;
    return this.http.get<any>(url, this.httpOptions)
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }


  getActiveOwners(page: number, size: number): Observable<any> {
    const url = `${this.baseUrl}/owners/active?page=${page}&size=${size}`;
    return this.http.get<any>(url, this.httpOptions)
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }


  changeOwnerStatus(ownerId: number): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/owners/${ownerId}/change-account-state`, {  withCredentials: true})
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

  deleteOwner(ownerId: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/owners/${ownerId}/delete`, {  withCredentials: true})
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

  updateOwnerImage(ownerId: number, formData: FormData): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/owners/${ownerId}/edit-image`, formData, {  withCredentials: true})
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }


  updateOwner(ownerId: number, body: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/owners/${ownerId}/edit`, body,this.httpOptions)
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

  updateOwnerPassword(ownerId: number, updatedOwnerPassword: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/owners/${ownerId}/edit-password`, updatedOwnerPassword, this.httpOptions)
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

}
