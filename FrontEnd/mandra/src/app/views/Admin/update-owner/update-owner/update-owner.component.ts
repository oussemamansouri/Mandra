import { OwnerService } from 'src/app/services/apiServices/OwnerService/owner.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-update-owner',
  templateUrl: './update-owner.component.html',
  styleUrls: ['./update-owner.component.css']
})
export class UpdateOwnerComponent implements OnInit {

  ownerId!: number
  owner: any
  errmessage!:string

  constructor(private OwnerService: OwnerService, private router: Router, private param: ActivatedRoute) { }

  ngOnInit(): void {
    this.loadOldOwner()
  }

  loadOldOwner() {
    this.ownerId = this.param.snapshot.queryParams['ownerId']
    this.OwnerService.getOwnerById(this.ownerId).subscribe(
      res => this.owner = res,
      (err: HttpErrorResponse) =>
        console.log("error to load owner")

    )
  }

  updateOwner(form:NgForm) {
    let body = form.value
    this.OwnerService.updateOwner(this.ownerId,body).subscribe(
      (res) =>
        this.router.navigate(['/admin/owners/details'], { queryParams: { ownerId: this.ownerId } }),
          (err:HttpErrorResponse) =>{
            console.log("error when updating owner"+err.message)
            this.errmessage = "ce email est prêt à être utilisé!"
          }
    )


  }

}
