import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RestaurantService } from 'src/app/services/apiServices/restaurantService/restaurant.service';

@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.css']
})
export class RestaurantComponent implements OnInit {



  restaurants!:any[]
  restaurant:any = {}
  page: number = 0;
  size: number = 12;
  totalPages: number = 0; // Initialize as 0
  baseURL!: string;


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


  loadRestaurants(){
    this.restaurantService.getRestaurants(this.page, this.size).subscribe({
      next: (info:any) => {
        this.restaurants = info.content || [];
        this.totalPages = info.totalPages || 0;
      },
      error:(err: HttpErrorResponse) => {
        console.error('Error loading restaurants :', err.message);
      }
  });
  }

  openModal(restaurant: any): void {
    this.restaurant = restaurant;
  }

  sendIdToUpdate(): void {
    // Code pour l'envoi de l'identifiant si nécessaire
  }

  deleteRestaurant(): void {
    this.restaurantService.deleteRestaurant(this.restaurant.id).subscribe(
      (res) =>
        this.loadRestaurants()
    ),(err:HttpErrorResponse)=>
       console.log("error while deletting restaurant")
  }


}
