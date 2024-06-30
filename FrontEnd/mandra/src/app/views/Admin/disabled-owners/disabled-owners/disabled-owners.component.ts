import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { OwnerService } from 'src/app/services/apiServices/OwnerService/owner.service';


@Component({
  selector: 'app-disabled-owners',
  templateUrl: './disabled-owners.component.html',
  styleUrls: ['./disabled-owners.component.css']
})
export class DisabledOwnersComponent implements OnInit, OnDestroy{
  searchTerm: any;
  baseURL!: string;
  owners: any[] = []; // Initialize as an empty array
  ownerid: any;
  page: number = 0;
  size: number = 10;
  totalPages: number = 0; // Initialize as 0
  subscriptions: Subscription = new Subscription();

  constructor(@Inject("BaseURL") private BaseURL: string, private ownerService: OwnerService,
   private router: Router) {
    this.baseURL = BaseURL;
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }


  ngOnInit(): void {
    this.loadOwners();
  }

  loadOwners(): void {
    const loadOwnersSub = this.ownerService.getDisabledOwners(this.page, this.size).subscribe(
      info => {
        this.owners = info.content || [];
        this.totalPages = info.totalPages || 0;
      },
      (err: HttpErrorResponse) => {
        console.error('Error loading owners:', err.message);
      }
    );
    this.subscriptions.add(loadOwnersSub);
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
      this.ownerService.deleteOwner(this.ownerid).subscribe(
        (res) => {
          console.log('Owner deleted successfully');
          this.loadOwners();
        },
        (err: HttpErrorResponse) => {
          console.error('Error while deleting owner:', err.message);
        }
      );
    } else {
      console.error('No owner ID provided for deletion');
    }
  }



  changeToActive(id: number) {
    this.ownerService.changeOwnerStatus(id).subscribe(
      res =>{
        this.loadOwners();
      },
      (err:HttpErrorResponse) =>
        console.log("Error while changing owner account state"+ err.message)
    )
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
