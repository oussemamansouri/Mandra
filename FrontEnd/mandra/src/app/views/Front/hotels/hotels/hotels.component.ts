import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HotelService } from 'src/app/services/apiServices/hotelService/hotel.service';

@Component({
  selector: 'app-hotels',
  templateUrl: './hotels.component.html',
  styleUrls: ['./hotels.component.css']
})
export class HotelsComponent implements OnInit{
  hotels: any[] = [];
  hotel: any = {};
  page: number = 0;
  size: number = 12;
  totalPages: number = 0;
  baseURL!: string;
  loading: boolean = false;
  searchTerm: string = '';

  constructor(private hotelService: HotelService, private router: Router, @Inject("BaseURL") private BaseURL: string) {
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

  loadHotels(): void {
    this.loading = true;
    this.hotelService.getHotels(this.page, this.size, this.searchTerm).subscribe({
      next: (info: any) => {
        this.hotels = info.content || [];
        this.totalPages = info.totalPages || 0;
        this.loading = false;
      },
      error: (err: HttpErrorResponse) => {
        console.error('Error loading hotels:', err.message);
        this.loading = false;
      }
    });
  }


  onSearchTermChange(): void {
    this.loadHotels();
  }

  openModal(hotel: any): void {
    this.hotel = hotel;
  }

  sendIdToUpdate(): void {
    // Code pour l'envoi de l'identifiant si nécessaire
  }



  goToHotels(){
    this.router.navigate(['/hotels'])
  }
  goToRestaurants(){
    this.router.navigate(['/restaurants'])
  }
  goToGuestHouses(){
    this.router.navigate(['/guast-house'])
  }
  goToGastros(){
    this.router.navigate(['/gastronomic-specialties'])
  }
  goToSpecialty(){
    this.router.navigate(['/specialty-women'])
  }


}
