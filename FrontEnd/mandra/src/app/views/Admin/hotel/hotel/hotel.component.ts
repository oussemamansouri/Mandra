import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HotelService } from 'src/app/services/apiServices/hotelService/hotel.service';

@Component({
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css']
})
export class HotelComponent implements OnInit {


  hotels!:any[]
  hotel:any = {}
  page: number = 0;
  size: number = 12;
  totalPages: number = 0; // Initialize as 0
  baseURL!: string;


  constructor( private hotelService:HotelService, private router:Router, @Inject("BaseURL") private BaseURL: string) {
    this.baseURL = BaseURL;
   }

   ngOnInit(): void {
    this.loadHotels();
  }

  onPageChange(newPage: number): void {
    if (newPage >= 0 && newPage < this.totalPages) {
      this.page = newPage;
      this.loadHotels();
    }
  }


  loadHotels(){
    this.hotelService.getHotels(this.page, this.size).subscribe({
      next: (info:any) => {
        this.hotels = info.content || [];
        this.totalPages = info.totalPages || 0;
      },
      error:(err: HttpErrorResponse) => {
        console.error('Error loading hotels:', err.message);
      }
  });
  }

  openModal(hotel: any): void {
    this.hotel = hotel;
  }

  sendIdToUpdate(): void {
    // Code pour l'envoi de l'identifiant si nécessaire
  }

  deleteHotel(): void {
    this.hotelService.deleteHotel(this.hotel.id).subscribe(
      (res) =>
        this.loadHotels()
    ),(err:HttpErrorResponse)=>
       console.log("error while deletting hotel")
  }


  



}
