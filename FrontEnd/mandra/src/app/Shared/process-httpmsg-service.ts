import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProcessHttpmsgService {

  constructor() { }

  public handleError(error:HttpErrorResponse|any){
    let errMsg:string;
    //(Client side error)
    if(error.error instanceof ErrorEvent){
      errMsg=error.error.message
    }else{ //Server side error
      errMsg=`Json server Error: An error has occured. please try again later-${error.message || ''}`
    }
    return throwError(()=>errMsg)
  }
}
