import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SpecialtyWomenService } from 'src/app/services/apiServices/specialtyWomenService/specialty-women.service';

@Component({
  selector: 'app-specialty-women',
  templateUrl: './specialty-women.component.html',
  styleUrls: ['./specialty-women.component.css']
})
export class SpecialtyWomenComponent implements OnInit {

  specialtyWomens!:any[]
  specialtyWomen:any = {}
  page: number = 0;
  size: number = 12;
  totalPages: number = 0; // Initialize as 0
  baseURL!: string;
  loading: boolean = false;
  searchTerm: string = '';

  constructor( private specialtyWomenService:SpecialtyWomenService, private router:Router, @Inject("BaseURL") private BaseURL: string) {
    this.baseURL = BaseURL;
   }

   ngOnInit(): void {
    this.loadSpecialtyWomens();
  }

  onPageChange(newPage: number): void {
    if (newPage >= 0 && newPage < this.totalPages) {
      this.page = newPage;
      this.loadSpecialtyWomens();
    }
  }

  onSearchTermChange(): void {
    this.loadSpecialtyWomens();
  }

  loadSpecialtyWomens(){
    this.loading = true;
    this.specialtyWomenService.getSpecialtyWomens(this.page, this.size, this.searchTerm).subscribe({
      next: (info:any) => {
        this.specialtyWomens = info.content || [];
        this.totalPages = info.totalPages || 0;
        this.loading = false;
      },
      error:(err: HttpErrorResponse) => {
        console.error('Error loading Specialty Womens :', err.message);
        this.loading = false;
      }
  });
  }

  openModal(specialtyWomen: any): void {
    this.specialtyWomen = specialtyWomen;
  }

  sendIdToUpdate(): void {
    // Code pour l'envoi de l'identifiant si nécessaire
  }

  deleteSpecialtyWomen(): void {
    this.specialtyWomenService.deleteSpecialtyWomen(this.specialtyWomen.id).subscribe(
      (res) =>
        this.loadSpecialtyWomens()
    ),(err:HttpErrorResponse)=>
       console.log("error while deletting Specialty Womens")
  }

}
