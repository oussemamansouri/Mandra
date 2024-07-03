import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { ProcessHttpmsgService } from 'src/app/Shared/process-httpmsg-service';

@Injectable({
  providedIn: 'root'
})
export class SpecialtyWomenService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };

  constructor(private http: HttpClient, @Inject('BaseURL') private baseUrl: string,
    private processHTTPMsgService: ProcessHttpmsgService) { }


    getSpecialtyWomens(page: number, size: number, searchTerm?: string): Observable<any> {
      let url = `${this.baseUrl}/specialtywomens?page=${page}&size=${size}`;
      if (searchTerm) {
        url += `&searchTerm=${searchTerm}`;
      }
      return this.http.get<any>(url, this.httpOptions)
        .pipe(catchError(this.processHTTPMsgService.handleError));
    }


    getSpecialtyWomenById(specialtyWomenId: number): Observable<any> {
      return this.http.get<any>(this.baseUrl + `/specialtywomens/${specialtyWomenId}`, this.httpOptions)
        .pipe(catchError(this.processHTTPMsgService.handleError));
    }


    deleteSpecialtyWomen(specialtyWomenId: number): Observable<any> {
      return this.http.delete<any>(`${this.baseUrl}/specialtywomens/${specialtyWomenId}/delete`, {  withCredentials: true})
        .pipe(catchError(this.processHTTPMsgService.handleError));
    }

}
