import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { ProcessHttpmsgService } from 'src/app/Shared/process-httpmsg-service';

@Injectable({
  providedIn: 'root'
})
export class HotelService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };

  constructor(private http: HttpClient, @Inject('BaseURL') private baseUrl: string,
    private processHTTPMsgService: ProcessHttpmsgService) { }


    getHotels(page: number, size: number): Observable<any> {
      const url = `${this.baseUrl}/hotels?page=${page}&size=${size}`;
      return this.http.get<any>(url, this.httpOptions)
        .pipe(catchError(this.processHTTPMsgService.handleError));
    }


    getHotelById(hotelId: number): Observable<any> {
      return this.http.get<any>(this.baseUrl + `/hotels/${hotelId}`, this.httpOptions)
        .pipe(catchError(this.processHTTPMsgService.handleError));
    }






}
