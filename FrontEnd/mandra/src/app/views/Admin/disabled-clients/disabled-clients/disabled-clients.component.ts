import { HttpErrorResponse } from '@angular/common/http';
import { ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClientService } from 'src/app/services/apiServices/ClientService/client.service';

@Component({
  selector: 'app-disabled-clients',
  templateUrl: './disabled-clients.component.html',
  styleUrls: ['./disabled-clients.component.css']
})
export class DisabledClientsComponent implements OnInit {
  searchTerm: any;
  baseURL!: string;
  clients: any[] = [];
  clientId: any;
  page: number = 0;
  size: number = 10;
  totalPages: number = 0;
  isLoading: boolean = true; 

  constructor(
    @Inject("BaseURL") private BaseURL: string,
    private clientService: ClientService,
    private changeDetectorRef: ChangeDetectorRef,
    private router: Router
  ) {
    this.baseURL = BaseURL;
  }

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.isLoading = true;
    this.clientService.getDisabledClients(this.page, this.size).subscribe({
      next: (info: any) => {
        this.clients = info.content || [];
        this.totalPages = info.totalPages || 0;
        this.isLoading = false;
        this.changeDetectorRef.detectChanges();
      },
      error: (err: HttpErrorResponse) => {
        console.error('Error loading clients:', err.message);
        this.isLoading = false;
      }
    });
  }

  onPageChange(newPage: number): void {
    if (newPage >= 0 && newPage < this.totalPages) {
      this.page = newPage;
      this.loadClients();
    }
  }

  getClientid(id: number) {
    this.clientId = id;
  }

  deleteClient(): void {
    if (this.clientId) {
      this.clientService.deleteClient(this.clientId).subscribe({
        next: (res) => {
          console.log('Client deleted successfully');
          this.loadClients();
        },
        error: (err: HttpErrorResponse) => {
          this.router.navigate(['/admin']);
          console.error('Error while deleting client:', err.message);
        }
      });
    } else {
      console.error('No client ID provided for deletion');
    }
  }

  changeToActive(id: number) {
    this.clientService.changeClientStatus(id).subscribe({
      next: () => {
        this.router.navigate(['/admin/clients/active']);
      },
      error: (err: HttpErrorResponse) =>
        console.log("Error while changing client account state")
    });
  }

  ViewDetails(id: any) {
    this.router.navigate(['/admin/clients/details'], { queryParams: { clientId: id } });
  }

  Update(id: any) {
    this.router.navigate(['/admin/clients/update'], { queryParams: { clientId: id } });
  }
}
