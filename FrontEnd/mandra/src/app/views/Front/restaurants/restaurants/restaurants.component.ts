import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RestaurantService } from 'src/app/services/apiServices/restaurantService/restaurant.service';

@Component({
  selector: 'app-restaurants',
  templateUrl: './restaurants.component.html',
  styleUrls: ['./restaurants.component.css']
})
export class RestaurantsComponent implements OnInit {

  restaurants!:any[]
  restaurant:any = {}
  page: number = 0;
  size: number = 12;
  totalPages: number = 0;
  baseURL!: string;
  loading: boolean = false;
  searchTerm: string = '';


  constructor( private restaurantService:RestaurantService, private router:Router, @Inject("BaseURL") private BaseURL: string) {
    this.baseURL = BaseURL;
   }

   ngOnInit(): void {
    this.loadRestaurants();
  }

  onPageChange(newPage: number): void {
    if (newPage >= 0 && newPage < this.totalPages) {
      this.page = newPage;
      this.loadRestaurants();
    }
  }

  onSearchTermChange(): void {
    this.loadRestaurants();
  }


  loadRestaurants(){
    this.loading = true;
    this.restaurantService.getRestaurants(this.page, this.size, this.searchTerm).subscribe({
      next: (info:any) => {
        this.restaurants = info.content || [];
        this.totalPages = info.totalPages || 0;
        this.loading = false;
      },
      error:(err: HttpErrorResponse) => {
        console.error('Error loading restaurants :', err.message);
        this.loading = false;
      }
  });
  }

  openModal(restaurant: any): void {
    this.restaurant = restaurant;
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
