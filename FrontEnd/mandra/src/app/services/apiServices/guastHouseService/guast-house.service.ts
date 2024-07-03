import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { ProcessHttpmsgService } from 'src/app/Shared/process-httpmsg-service';

@Injectable({
  providedIn: 'root'
})
export class GuastHouseService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };

  constructor(private http: HttpClient, @Inject('BaseURL') private baseUrl: string,
    private processHTTPMsgService: ProcessHttpmsgService) { }


    getGuastHouses(page: number, size: number, searchTerm?: string): Observable<any> {
      let url = `${this.baseUrl}/guesthouses?page=${page}&size=${size}`;
      if (searchTerm) {
        url += `&searchTerm=${searchTerm}`;
      }
      return this.http.get<any>(url, this.httpOptions)
        .pipe(catchError(this.processHTTPMsgService.handleError));
    }



    getGuastHouseById(guastHousId: number): Observable<any> {
      return this.http.get<any>(this.baseUrl + `/guesthouses/${guastHousId}`, this.httpOptions)
        .pipe(catchError(this.processHTTPMsgService.handleError));
    }


    deleteGuastHouse(guastHousId: number): Observable<any> {
      return this.http.delete<any>(`${this.baseUrl}/guesthouses/${guastHousId}/delete`, {  withCredentials: true})
        .pipe(catchError(this.processHTTPMsgService.handleError));
    }


}
