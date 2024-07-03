import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GuastHouseService } from 'src/app/services/apiServices/guastHouseService/guast-house.service';

@Component({
  selector: 'app-guest-houses',
  templateUrl: './guest-houses.component.html',
  styleUrls: ['./guest-houses.component.css']
})
export class GuestHousesComponent implements OnInit {

  guastHouses: any[] = [];
  guastHouse: any = {};
  page: number = 0;
  size: number = 12;
  totalPages: number = 0;
  baseURL!: string;
  loading: boolean = false;
  searchTerm: string = '';

  constructor(private guastHouseService: GuastHouseService, private router: Router, @Inject("BaseURL") private BaseURL: string) {
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

  onSearchTermChange(): void {
    this.loadGuastHouses();
  }

  loadGuastHouses(): void {
    this.loading = true;
    this.guastHouseService.getGuastHouses(this.page, this.size, this.searchTerm).subscribe({
      next: (info: any) => {
        this.guastHouses = info.content || [];
        this.totalPages = info.totalPages || 0;
        this.loading = false;
      },
      error: (err: HttpErrorResponse) => {
        console.error('Error loading guest houses:', err.message);
        this.loading = false;
      }
    });
  }

  openModal(guastHouse: any): void {
    this.guastHouse = guastHouse;
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
