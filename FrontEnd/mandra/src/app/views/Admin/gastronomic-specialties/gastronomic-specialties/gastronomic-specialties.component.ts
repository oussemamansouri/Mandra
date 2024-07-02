import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GastronomicSpecialtiesService } from 'src/app/services/apiServices/gastronomicSpecialtiesService/gastronomic-specialties.service';

@Component({
  selector: 'app-gastronomic-specialties',
  templateUrl: './gastronomic-specialties.component.html',
  styleUrls: ['./gastronomic-specialties.component.css']
})
export class GastronomicSpecialtiesComponent implements OnInit {

  gastronomicSpecialties!: any[];
  gastronomicSpecialtie: any = {};
  page: number = 0;
  size: number = 12;
  totalPages: number = 0; // Initialize as 0
  baseURL!: string;
  loading: boolean = false; // Add loading state

  constructor(
    private gastronomicSpecialtiesService: GastronomicSpecialtiesService,
    private router: Router,
    @Inject("BaseURL") private BaseURL: string
  ) {
    this.baseURL = BaseURL;
  }

  ngOnInit(): void {
    this.loadGastronomicSpecialties();
  }

  onPageChange(newPage: number): void {
    if (newPage >= 0 && newPage < this.totalPages) {
      this.page = newPage;
      this.loadGastronomicSpecialties();
    }
  }

  loadGastronomicSpecialties(): void {
    this.loading = true; // Set loading to true
    this.gastronomicSpecialtiesService.getGastronomicSpecialties(this.page, this.size).subscribe({
      next: (info: any) => {
        this.gastronomicSpecialties = info.content || [];
        this.totalPages = info.totalPages || 0;
        this.loading = false; // Set loading to false when done
      },
      error: (err: HttpErrorResponse) => {
        console.error('Error loading gastronomic specialties:', err.message);
        this.loading = false; // Set loading to false on error
      }
    });
  }

  openModal(gastronomicSpecialtie: any): void {
    this.gastronomicSpecialtie = gastronomicSpecialtie;
  }

  sendIdToUpdate(): void {
    // Code pour l'envoi de l'identifiant si nécessaire
  }

  deleteGastronomicSpecialties(): void {
    this.gastronomicSpecialtiesService.deleteGastronomicSpecialtie(this.gastronomicSpecialtie.id).subscribe({
      next: (res) => {
        this.loadGastronomicSpecialties();
      },
      error: (err: HttpErrorResponse) => {
        console.error('Error while deleting gastronomic specialty:', err.message);
      }
    });
  }
}
