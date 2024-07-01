import { IMAGE_CONFIG } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ClientService } from 'src/app/services/apiServices/ClientService/client.service';

@Component({
  selector: 'app-update-client',
  templateUrl: './update-client.component.html',
  styleUrls: ['./update-client.component.css']
})
export class UpdateClientComponent implements OnInit {

  clientId!: number
  client: any
  errmessage!:string

  constructor(private clientService: ClientService, private router: Router, private param: ActivatedRoute) { }

  ngOnInit(): void {
    this.loadOldClient()
  }

  loadOldClient() {
    this.clientId = this.param.snapshot.queryParams['clientId']
    console.log(this.clientId)
    this.clientService.getClientById(this.clientId).subscribe(
      res => this.client = res,
      (err: HttpErrorResponse) =>
        console.log("error to load client")
    )
  }

  updateClient(form:NgForm) {
    let body = form.value
    this.clientService.updateClient(this.clientId,body).subscribe(
      (res) =>
        this.router.navigate(['/admin/clients/details'], { queryParams: { clientId: this.clientId } }),
          (err:HttpErrorResponse) =>{
            console.log("error when updating client"+err.message)
            this.errmessage = "ce email est prêt à être utilisé!"
          }
    )
  }



}
