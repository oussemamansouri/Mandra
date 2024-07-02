import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GuastHouseService } from 'src/app/services/apiServices/guastHouseService/guast-house.service';
import { HotelService } from 'src/app/services/apiServices/hotelService/hotel.service';

@Component({
  selector: 'app-guast-house',
  templateUrl: './guast-house.component.html',
  styleUrls: ['./guast-house.component.css']
})
export class GuastHouseComponent implements OnInit {


  guastHouses!:any[]
  guastHouse:any = {}
  page: number = 0;
  size: number = 12;
  totalPages: number = 0; // Initialize as 0
  baseURL!: string;


  constructor( private guastHouseService:GuastHouseService, private router:Router, @Inject("BaseURL") private BaseURL: string) {
    this.baseURL = BaseURL;
   }

   ngOnInit(): void {
    this.loadGuastHouses();
  }

  onPageChange(newPage: number): void {
    if (newPage >= 0 && newPage < this.totalPages) {
      this.page = newPage;
      this.loadGuastHouses();
    }
  }


  loadGuastHouses(){
    this.guastHouseService.getGuastHouses(this.page, this.size).subscribe({
      next: (info:any) => {
        this.guastHouses = info.content || [];
        this.totalPages = info.totalPages || 0;
      },
      error:(err: HttpErrorResponse) => {
        console.error('Error loading guast Houses:', err.message);
      }
  });
  }

  openModal(guastHouse: any): void {
    this.guastHouse = guastHouse;
  }

  sendIdToUpdate(): void {
    // Code pour l'envoi de l'identifiant si nécessaire
  }

  deleteGuastHouse(): void {
    this.guastHouseService.deleteGuastHouse(this.guastHouse.id).subscribe(
      (res) =>
        this.loadGuastHouses()
    ),(err:HttpErrorResponse)=>
      this.router.navigate(['/admin'])
       console.log("error while deletting maisons d'hôtes")
  }
}
