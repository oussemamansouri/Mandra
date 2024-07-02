import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { ProcessHttpmsgService } from 'src/app/Shared/process-httpmsg-service';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {


  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
    withCredentials: true
  };

  constructor(private http: HttpClient, @Inject('BaseURL') private baseUrl: string,
    private processHTTPMsgService: ProcessHttpmsgService) { }


    getRestaurants(page: number, size: number): Observable<any> {
      const url = `${this.baseUrl}/restaurants?page=${page}&size=${size}`;
      return this.http.get<any>(url, this.httpOptions)
        .pipe(catchError(this.processHTTPMsgService.handleError));
    }


    getRestaurantById(restaurantId: number): Observable<any> {
      return this.http.get<any>(this.baseUrl + `/restaurants/${restaurantId}`, this.httpOptions)
        .pipe(catchError(this.processHTTPMsgService.handleError));
    }


    deleteRestaurant(restaurantId: number): Observable<any> {
      return this.http.delete<any>(`${this.baseUrl}/restaurants/${restaurantId}/delete`, {  withCredentials: true})
        .pipe(catchError(this.processHTTPMsgService.handleError));
    }
}
