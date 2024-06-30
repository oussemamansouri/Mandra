import { HttpErrorResponse } from '@angular/common/http';
import { ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OwnerService } from 'src/app/services/apiServices/OwnerService/owner.service';


@Component({
  selector: 'app-disabled-owners',
  templateUrl: './disabled-owners.component.html',
  styleUrls: ['./disabled-owners.component.css']
})
export class DisabledOwnersComponent implements OnInit{
  searchTerm: any;
  baseURL!: string;
  owners: any[] = []; // Initialize as an empty array
  ownerid: any;
  page: number = 0;
  size: number = 10;
  totalPages: number = 0; // Initialize as 0

  constructor(@Inject("BaseURL") private BaseURL: string, private ownerService: OwnerService,
  private changeDetectorRef: ChangeDetectorRef,
   private router: Router) {
    this.baseURL = BaseURL;
  }




  ngOnInit(): void {
    this.loadOwners();
    //  this.changeDetectorRef.detectChanges();
  }

  loadOwners(): void {
    this.ownerService.getDisabledOwners(this.page, this.size).subscribe({
      next: (info:any) => {
        this.owners = info.content || [];
        this.totalPages = info.totalPages || 0;
        this.changeDetectorRef.detectChanges();
      },
      error:(err: HttpErrorResponse) => {
        console.error('Error loading owners:', err.message);
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
    this.ownerid = id
  }

  deleteOwner(): void {
    if (this.ownerid) {
      this.ownerService.deleteOwner(this.ownerid).subscribe({
        next:(res:any) => {
          console.log('Owner deleted successfully');
          this.loadOwners();
          // this.changeDetectorRef.detectChanges();
        },
        error:(err: HttpErrorResponse) => {
          console.error('Error while deleting owner:', err.message);
        }
    });
    } else {
      console.error('No owner ID provided for deletion');
    }
  }



  changeToActive(id: number) {
    this.ownerService.changeOwnerStatus(id).subscribe({
      next:() =>{
        // this.loadOwners();
        console.log("Error while changing owner account state")
      },
      error:(err:HttpErrorResponse) =>
        console.log("Error while changing owner account state")
  })
  }

  sendId(id: any) {
    this.router.navigate(['/admin/owners/details'], { queryParams: { ownerId: id } })

  }
  sendOwnerId(id: any) {
    this.router.navigate(['/admin/owners/update'], { queryParams: { ownerId: id } })
  }




  // filter() {
  //   if (!this.searchTerm) {
  //     return this.owners;
  //   }

  //   const filteredClients = this.owners.filter((client: any) => {
  //     const firstNameMatch = client.firstname.toLowerCase().includes(this.searchTerm.toLowerCase());
  //     const lastNameMatch = client.lastname.toLowerCase().includes(this.searchTerm.toLowerCase());

  //     return firstNameMatch || lastNameMatch;
  //   });

  //   return filteredClients;
  // }




}
