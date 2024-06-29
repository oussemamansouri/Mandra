import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { ProcessHttpmsgService } from 'src/app/Shared/process-httpmsg-service';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };

  httpSimpleOptions = {
    withCredentials: true
  };

  constructor(private http: HttpClient, @Inject('BaseURL') private baseUrl: string,
    private processHTTPMsgService: ProcessHttpmsgService) { }


  getAdmin(adminId: number): Observable<any> {
    return this.http.get<any>(this.baseUrl + `/admin/${adminId}`, this.httpOptions)
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

  updateAdminImage(adminId: number, formData: FormData): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/admin/${adminId}/edit-image`, formData, this.httpSimpleOptions)
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

  updateAdmin(adminId: number, updatedAdmin: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/admin/${adminId}/edit`, updatedAdmin, this.httpOptions)
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }


  updateAdminPassword(adminId: number, updatedAdminPassword: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/admin/${adminId}/edit-password`, updatedAdminPassword, this.httpOptions)
      .pipe(catchError(this.processHTTPMsgService.handleError));
  }

}
