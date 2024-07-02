import { GuastHouseService } from './../../../../services/apiServices/guastHouseService/guast-house.service';
import { BaseURL } from 'src/app/Shared/base-url';
import { Component, Inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OwnerService } from 'src/app/services/apiServices/OwnerService/owner.service';
import { HttpErrorResponse } from '@angular/common/http';
import { HotelService } from 'src/app/services/apiServices/hotelService/hotel.service';
import { RestaurantService } from 'src/app/services/apiServices/restaurantService/restaurant.service';

@Component({
  selector: 'app-owner-details',
  templateUrl: './owner-details.component.html',
  styleUrls: ['./owner-details.component.css']
})
export class OwnerDetailsComponent implements OnInit{

  ownerId: any
  owner: any
  imagepath!: string
  ownerHotels:any[] = []
  ownerGuastHouses:any[] = []
  ownerRestaurants:any[] = []
  hotel:any = {}
  restaurant:any = {}
  guastHouse:any = {}

  /////////// later
  participation: any
  participants: any = []
  achat: any
  formationid: any
  formation: any = { date_debut: "", date_fin: "", id: "", titre: "", discription: "", img: "", prix: "", heures: "", promotion: "", categorie: "", etat: "", diplome: "", certifiee: "", createdAt: "", updatedAt: "", CentreId: "", Centre: {} }
  buys: any = []
  ebookid: any
  ebook: any = { id: '', titre: '', discription: '', auteur: '', format: '', nb_pages: '', img: '', prix: '', promotion: '', book: '', createdAt: '', updatedAt: '', CentreId: '' }
////////////

  constructor(private route: ActivatedRoute, private ownerService: OwnerService, private router: Router,
    @Inject('BaseURL') private BaseURL: string, private hotelService:HotelService, private guastHouseService:GuastHouseService,
    private restaurantService:RestaurantService
  ) { this.imagepath = this.BaseURL }

  ngOnInit(): void {
    this.loadOwner()
  }

  loadOwner() {
    this.ownerId = this.route.snapshot.queryParams['ownerId']
    this.ownerService.getOwnerById(this.ownerId).subscribe({
      next: (data) =>{
        this.owner = data
        this.ownerHotels = data.hotels
        this.ownerGuastHouses = data.guesthouses
        this.ownerRestaurants = data.restaurants
      },error: (err: HttpErrorResponse) =>
        console.log('error when traying to load owner' + err.message)
    })
  }

  updateimage(event: any) {
    if (event.target.files.length > 0) {
      const path = event.target.files[0];
      const formData = new FormData();
      formData.append('image', path)
      this.ownerService.updateOwnerImage(this.ownerId, formData).subscribe(info =>
        this.router.navigate(['/admin']),
        (err: HttpErrorResponse) =>
          console.log("error when trying to upload the owner image!" + err.message))
    }
  }

  deleteOwner() {
    this.ownerService.deleteOwner(this.ownerId).subscribe(info =>
      this.router.navigate(['/admin']),
      (err: HttpErrorResponse) =>
        // console.log("error when trying to delete the owner!" + err.message))
      this.router.navigate(['/admin'])) // i will fix that later

  }

  sendOwnerToUpdate() {
    this.router.navigate(['/admin/owners/update'],{queryParams:{ownerId:this.ownerId}})
  }

  changeAccountStauts() {
    this.ownerService.changeOwnerStatus(this.ownerId).subscribe({
      next:() =>{
        this.loadOwner();
      },
      error:(err:HttpErrorResponse) =>
        console.log("Error while changing owner account state")
  })
  }


  openHotelModal(hotel: any): void {
    this.hotel = hotel;
  }


  deleteHotel(): void {
    this.hotelService.deleteHotel(this.hotel.id).subscribe(
      (res) =>
        this.loadOwner()
    ),(err:HttpErrorResponse)=>
       console.log("error while deletting hotel")
  }



  openRestaurantModal(restaurant: any): void {
    this.restaurant = restaurant;
  }

  openGuastHouseModal(guastHouse: any): void {
    this.guastHouse = guastHouse;
  }


  deleteRestaurant(): void {
    this.restaurantService.deleteRestaurant(this.restaurant.id).subscribe(
      (res) =>
        this.loadOwner()
    ),(err:HttpErrorResponse)=>
       console.log("error while deletting restaurant")
  }



  deleteGuastHouse(): void {
    this.guastHouseService.deleteGuastHouse(this.guastHouse.id).subscribe(
      (res) =>
        this.loadOwner()
    ),(err:HttpErrorResponse)=>
      this.router.navigate(['/admin'])
       console.log("error while deletting maisons d'hôtes")
  }



}
