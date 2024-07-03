import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { ProcessHttpmsgService } from 'src/app/Shared/process-httpmsg-service';

@Injectable({
  providedIn: 'root'
})
export class GastronomicSpecialtiesService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };

  constructor(private http: HttpClient, @Inject('BaseURL') private baseUrl: string,
    private processHTTPMsgService: ProcessHttpmsgService) { }


    getGastronomicSpecialties(page: number, size: number, searchTerm?: string): Observable<any> {
      let url = `${this.baseUrl}/gastronomicspecialties?page=${page}&size=${size}`;
      if (searchTerm) {
        url += `&searchTerm=${searchTerm}`;
      }
      return this.http.get<any>(url, this.httpOptions)
        .pipe(catchError(this.processHTTPMsgService.handleError));
    }


    getGastronomicSpecialtieById(gastronomicspecialtieId: number): Observable<any> {
      return this.http.get<any>(this.baseUrl + `/gastronomicspecialties/${gastronomicspecialtieId}`, this.httpOptions)
        .pipe(catchError(this.processHTTPMsgService.handleError));
    }


    deleteGastronomicSpecialtie(gastronomicspecialtieId: number): Observable<any> {
      return this.http.delete<any>(`${this.baseUrl}/gastronomicspecialties/${gastronomicspecialtieId}/delete`, {  withCredentials: true})
        .pipe(catchError(this.processHTTPMsgService.handleError));
    }

}
