import { HttpErrorResponse } from '@angular/common/http';
import { ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OwnerService } from 'src/app/services/apiServices/OwnerService/owner.service';

@Component({
  selector: 'app-disabled-owners',
  templateUrl: './disabled-owners.component.html',
  styleUrls: ['./disabled-owners.component.css']
})
export class DisabledOwnersComponent implements OnInit {
  searchTerm: any;
  baseURL!: string;
  owners: any[] = [];
  ownerid: any;
  page: number = 0;
  size: number = 10;
  totalPages: number = 0;
  isLoading: boolean = true; // Add this line

  constructor(
    @Inject("BaseURL") private BaseURL: string,
    private ownerService: OwnerService,
    private changeDetectorRef: ChangeDetectorRef,
    private router: Router
  ) {
    this.baseURL = BaseURL;
  }

  ngOnInit(): void {
    this.loadOwners();
  }

  loadOwners(): void {
    this.isLoading = true; 
    this.ownerService.getDisabledOwners(this.page, this.size).subscribe({
      next: (info: any) => {
        this.owners = info.content || [];
        this.totalPages = info.totalPages || 0;
        this.isLoading = false;
        this.changeDetectorRef.detectChanges();
      },
      error: (err: HttpErrorResponse) => {
        console.error('Error loading owners:', err.message);
        this.isLoading = false;
      }
    });
  }

  onPageChange(newPage: number): void {
    if (newPage >= 0 && newPage < this.totalPages) {
      this.page = newPage;
      this.loadOwners();
    }
  }

  getOwnerid(id: any) {
    this.ownerid = id;
  }

  deleteOwner(): void {
    if (this.ownerid) {
      this.ownerService.deleteOwner(this.ownerid).subscribe({
        next: (res: any) => {
          console.log('Owner deleted successfully');
          this.loadOwners();
        },
        error: (err: HttpErrorResponse) => {
          console.error('Error while deleting owner:', err.message);
          this.router.navigate(['/admin']);
        }
      });
    } else {
      console.error('No owner ID provided for deletion');
    }
  }

  changeToActive(id: number) {
    this.ownerService.changeOwnerStatus(id).subscribe({
      next: () => {
        this.router.navigate(['/admin/owners/active']);
      },
      error: (err: HttpErrorResponse) =>
        console.log("Error while changing owner account state")
    });
  }

  sendId(id: any) {
    this.router.navigate(['/admin/owners/details'], { queryParams: { ownerId: id } });
  }

  sendOwnerId(id: any) {
    this.router.navigate(['/admin/owners/update'], { queryParams: { ownerId: id } });
  }
}
